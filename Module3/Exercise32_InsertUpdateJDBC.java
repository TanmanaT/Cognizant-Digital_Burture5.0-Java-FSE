import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class StudentDAO {
    private String url;
    
    public StudentDAO(String dbUrl) {
        this.url = dbUrl;
    }
    
    public void insertStudent(String name, String city) {
        String insertSQL = "INSERT INTO students (name, city) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Inserted student '" + name + "'. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error inserting student: " + e.getMessage());
        }
    }
    
    public void updateStudentCity(int id, String newCity) {
        String updateSQL = "UPDATE students SET city = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, newCity);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Updated student ID " + id + " to city '" + newCity + "'. Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }
}

public class Exercise32_InsertUpdateJDBC {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        
        // Ensure table exists
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, city TEXT NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Setup error: " + e.getMessage());
        }
        
        StudentDAO dao = new StudentDAO(url);
        
        // Perform insert and update operations
        dao.insertStudent("Jane Smith", "Los Angeles");
        dao.updateStudentCity(1, "San Francisco"); // Update first student
    }
}
