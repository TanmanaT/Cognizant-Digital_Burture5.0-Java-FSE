import java.util.Scanner;

public class Exercise09_GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter marks (0-100): ");
            if (scanner.hasNextInt()) {
                int marks = scanner.nextInt();
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks! Please enter a value between 0 and 100.");
                } else {
                    char grade;
                    if (marks >= 90) {
                        grade = 'A';
                    } else if (marks >= 80) {
                        grade = 'B';
                    } else if (marks >= 70) {
                        grade = 'C';
                    } else if (marks >= 60) {
                        grade = 'D';
                    } else {
                        grade = 'F';
                    }
                    System.out.println("Assigned Grade: " + grade);
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
