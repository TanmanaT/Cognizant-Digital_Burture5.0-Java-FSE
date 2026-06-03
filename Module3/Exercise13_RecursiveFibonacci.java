import java.util.Scanner;

public class Exercise13_RecursiveFibonacci {
    public static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a positive integer n: ");
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n < 0) {
                    System.out.println("Fibonacci term index cannot be negative.");
                } else {
                    int result = fibonacci(n);
                    System.out.println("Fibonacci number at position " + n + " is " + result);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
