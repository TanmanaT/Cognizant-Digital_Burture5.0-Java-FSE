import java.util.Scanner;

public class Exercise16_PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();
            
            // Remove non-alphanumeric characters and convert to lowercase
            String cleaned = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            
            String reversed = new StringBuilder(cleaned).reverse().toString();
            
            if (cleaned.equals(reversed)) {
                System.out.println("The cleaned string \"" + cleaned + "\" is a palindrome.");
            } else {
                System.out.println("The cleaned string \"" + cleaned + "\" is not a palindrome.");
            }
        } finally {
            scanner.close();
        }
    }
}
