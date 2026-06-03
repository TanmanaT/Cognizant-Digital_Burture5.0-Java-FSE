import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercise31_BasicJDBC {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:students.db";
        
        System.out.println("Connecting to database: " + url);
        
        // JDBC driver registration is automatic in modern JDBC, but we try to load class for compatibility
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("SQLite JDBC Driver not found in classpath. Note: The code compiles successfully. Running it requires the SQLite JDBC driver jar.");
        }
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
             
            // Create students table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS students (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "name TEXT NOT NULL, " +
                                    "city TEXT NOT NULL)";
            stmt.execute(createTableSQL);
            System.out.println("Table 'students' verified/created.");
            
            // Insert seed record if empty
            ResultSet checkRs = stmt.executeQuery("SELECT count(*) AS cnt FROM students");
            if (checkRs.next() && checkRs.getInt("cnt") == 0) {
                stmt.execute("INSERT INTO students (name, city) VALUES ('John Doe', 'New York')");
                System.out.println("Inserted seed record: John Doe");
            }
            
            // Retrieve and print records
            String selectSQL = "SELECT id, name, city FROM students";
            try (ResultSet rs = stmt.executeQuery(selectSQL)) {
                System.out.println("\nRetrieving data from 'students' table:");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String city = rs.getString("city");
                    System.out.println("ID: " + id + ", Name: " + name + ", City: " + city);
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }
}
