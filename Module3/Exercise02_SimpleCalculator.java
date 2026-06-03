import java.util.Scanner;

public class Exercise02_SimpleCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter first number: ");
            double num1 = scanner.nextDouble();
            
            System.out.print("Enter second number: ");
            double num2 = scanner.nextDouble();
            
            System.out.print("Choose an operation (+, -, *, /): ");
            char operator = scanner.next().charAt(0);
            
            double result = 0;
            boolean valid = true;
            
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero is not allowed.");
                        valid = false;
                    } else {
                        result = num1 / num2;
                    }
                    break;
                default:
                    System.out.println("Invalid operation selected.");
                    valid = false;
            }
            
            if (valid) {
                System.out.println("Result: " + result);
            }
        } finally {
            scanner.close();
        }
    }
}
