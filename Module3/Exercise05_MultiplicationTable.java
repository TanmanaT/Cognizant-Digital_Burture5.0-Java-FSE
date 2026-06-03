import java.util.Scanner;

public class Exercise05_MultiplicationTable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                System.out.println("Multiplication table for " + number + ":");
                for (int i = 1; i <= 10; i++) {
                    System.out.println(number + " x " + i + " = " + (number * i));
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
