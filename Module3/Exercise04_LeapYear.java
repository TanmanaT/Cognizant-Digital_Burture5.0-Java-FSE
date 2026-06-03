import java.util.Scanner;

public class Exercise04_LeapYear {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a year: ");
            if (scanner.hasNextInt()) {
                int year = scanner.nextInt();
                boolean isLeap = false;
                
                if (year % 4 == 0) {
                    if (year % 100 == 0) {
                        if (year % 400 == 0) {
                            isLeap = true;
                        }
                    } else {
                        isLeap = true;
                    }
                }
                
                if (isLeap) {
                    System.out.println(year + " is a leap year.");
                } else {
                    System.out.println(year + " is not a leap year.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid year.");
            }
        } finally {
            scanner.close();
        }
    }
}
