import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Exercise33_TransactionJDBC {
    private static final String URL = "jdbc:sqlite:bank.db";
    
    public static void setupDatabase() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (" +
                         "id INTEGER PRIMARY KEY, " +
                         "owner TEXT NOT NULL, " +
                         "balance REAL NOT NULL)");
            
            // Seed two accounts if table is empty
            stmt.execute("DELETE FROM accounts"); // Reset for demonstration
            stmt.execute("INSERT INTO accounts (id, owner, balance) VALUES (1, 'Alice', 1000.0)");
            stmt.execute("INSERT INTO accounts (id, owner, balance) VALUES (2, 'Bob', 500.0)");
            System.out.println("Database reset. Alice (ID 1) balance: $1000.0. Bob (ID 2) balance: $500.0.");
            
        } catch (SQLException e) {
            System.out.println("Setup error: " + e.getMessage());
        }
    }
    
    public static void transferMoney(int fromId, int toId, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ? AND balance >= ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            conn.setAutoCommit(false); // Start transaction
            
            // Debit from source account
            try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL)) {
                debitStmt.setDouble(1, amount);
                debitStmt.setInt(2, fromId);
                debitStmt.setDouble(3, amount);
                int updated = debitStmt.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Debit failed. Account does not exist or insufficient balance.");
                }
            }
            
            // Credit to target account
            try (PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {
                creditStmt.setDouble(1, amount);
                creditStmt.setInt(2, toId);
                int updated = creditStmt.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Credit failed. Target account does not exist.");
                }
            }
            
            conn.commit(); // Commit transaction if both succeeded
            System.out.println("Transaction committed successfully. Transferred $" + amount + " from ID " + fromId + " to ID " + toId);
            
        } catch (SQLException e) {
            System.out.println("Transaction failed: " + e.getMessage() + ". Rolling back transaction...");
            if (conn != null) {
                try {
                    conn.rollback();
                    System.out.println("Rollback complete.");
                } catch (SQLException ex) {
                    System.out.println("Rollback failed: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // Ignore
                }
            }
        }
    }
    
    public static void main(String[] args) {
        setupDatabase();
        // 1. Success transfer
        transferMoney(1, 2, 200.0);
        
        // 2. Failed transfer (insufficient balance)
        transferMoney(1, 2, 9000.0);
    }
}
