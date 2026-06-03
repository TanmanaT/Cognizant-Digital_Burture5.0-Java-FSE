import java.util.Scanner;

public class Exercise15_StringReversal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a string: ");
            String input = scanner.nextLine();
            
            // Using StringBuilder to reverse
            String reversed = new StringBuilder(input).reverse().toString();
            
            System.out.println("Reversed string: " + reversed);
        } finally {
            scanner.close();
        }
    }
}
