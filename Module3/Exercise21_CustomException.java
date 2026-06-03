import java.util.Scanner;

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class Exercise21_CustomException {
    public static void checkAge(int age) throws InvalidAgeException {
        if (age < 18) {
            throw new InvalidAgeException("Age is less than 18. Access denied.");
        }
        System.out.println("Age is valid. Access granted.");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter your age: ");
            if (scanner.hasNextInt()) {
                int age = scanner.nextInt();
                try {
                    checkAge(age);
                } catch (InvalidAgeException e) {
                    System.out.println("Caught custom exception: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
