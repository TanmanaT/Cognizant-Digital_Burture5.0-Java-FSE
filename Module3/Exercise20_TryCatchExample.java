import java.util.Scanner;

public class Exercise20_TryCatchExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first integer: ");
            int num1 = scanner.nextInt();
            
            System.out.print("Enter second integer: ");
            int num2 = scanner.nextInt();
            
            try {
                int result = num1 / num2;
                System.out.println("Result: " + result);
            } catch (ArithmeticException e) {
                System.out.println("Exception caught: Division by zero is undefined! (" + e.getMessage() + ")");
            }
        } finally {
            scanner.close();
        }
    }
}
