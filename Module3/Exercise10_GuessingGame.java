import java.util.Scanner;
import java.util.Random;

public class Exercise10_GuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int targetNumber = random.nextInt(100) + 1; // 1 to 100
        int attempts = 0;
        int guess = 0;
        
        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I have selected a number between 1 and 100. Try to guess it!");
        
        try {
            while (guess != targetNumber) {
                System.out.print("Enter your guess: ");
                if (scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                    attempts++;
                    
                    if (guess < targetNumber) {
                        System.out.println("Too low! Try again.");
                    } else if (guess > targetNumber) {
                        System.out.println("Too high! Try again.");
                    } else {
                        System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // Clear invalid input
                }
            }
        } finally {
            scanner.close();
        }
    }
}
