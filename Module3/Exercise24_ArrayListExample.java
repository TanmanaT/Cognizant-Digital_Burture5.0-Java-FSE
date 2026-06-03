import java.util.ArrayList;
import java.util.Scanner;

public class Exercise24_ArrayListExample {
    public static void main(String[] args) {
        ArrayList<String> students = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("ArrayList Student Names Management");
            while (true) {
                System.out.print("Enter student name (or type 'exit' to finish): ");
                String name = scanner.nextLine().trim();
                if (name.equalsIgnoreCase("exit")) {
                    break;
                }
                if (!name.isEmpty()) {
                    students.add(name);
                    System.out.println("Added: " + name);
                }
            }
            
            System.out.println("\nAll entered student names:");
            for (String name : students) {
                System.out.println("- " + name);
            }
        } finally {
            scanner.close();
        }
    }
}
