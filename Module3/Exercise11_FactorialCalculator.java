import java.util.Scanner;

public class Exercise11_FactorialCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a non-negative integer: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number < 0) {
                    System.out.println("Factorial is not defined for negative numbers.");
                } else {
                    long factorial = 1;
                    for (int i = 1; i <= number; i++) {
                        factorial *= i;
                    }
                    System.out.println("Factorial of " + number + " is " + factorial);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
