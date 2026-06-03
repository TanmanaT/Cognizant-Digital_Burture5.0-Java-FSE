import java.util.HashMap;
import java.util.Scanner;

public class Exercise25_HashMapExample {
    public static void main(String[] args) {
        HashMap<Integer, String> studentMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("HashMap Student ID Mapping");
            // Seeding some initial values
            studentMap.put(101, "Alice");
            studentMap.put(102, "Bob");
            studentMap.put(103, "Charlie");
            
            System.out.println("Initial entries: " + studentMap);
            
            while (true) {
                System.out.print("Do you want to add a new student entry? (yes/no): ");
                String choice = scanner.nextLine().trim().toLowerCase();
                if (choice.equals("no")) {
                    break;
                }
                
                System.out.print("Enter Student ID (Integer): ");
                if (scanner.hasNextInt()) {
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Clear newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine().trim();
                    studentMap.put(id, name);
                    System.out.println("Added/Updated ID " + id + " -> " + name);
                } else {
                    System.out.println("Invalid ID. Must be an integer.");
                    scanner.nextLine(); // Clear invalid input
                }
            }
            
            System.out.print("\nEnter Student ID to retrieve name: ");
            if (scanner.hasNextInt()) {
                int searchId = scanner.nextInt();
                if (studentMap.containsKey(searchId)) {
                    System.out.println("Student Name: " + studentMap.get(searchId));
                } else {
                    System.out.println("No student found with ID " + searchId);
                }
            } else {
                System.out.println("Invalid input.");
            }
        } finally {
            scanner.close();
        }
    }
}
