import java.util.Scanner;

public class Exercise03_EvenOrOdd {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter an integer: ");
            if (scanner.hasNextInt()) {
                int number = scanner.nextInt();
                if (number % 2 == 0) {
                    System.out.println(number + " is Even.");
                } else {
                    System.out.println(number + " is Odd.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
