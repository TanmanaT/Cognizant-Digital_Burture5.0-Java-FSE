import java.util.Scanner;

public class Exercise14_ArraySumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter the number of elements: ");
            if (scanner.hasNextInt()) {
                int size = scanner.nextInt();
                if (size <= 0) {
                    System.out.println("Array size must be a positive integer.");
                    return;
                }
                
                double[] array = new double[size];
                System.out.println("Enter " + size + " elements:");
                for (int i = 0; i < size; i++) {
                    System.out.print("Element " + (i + 1) + ": ");
                    array[i] = scanner.nextDouble();
                }
                
                double sum = 0;
                for (double val : array) {
                    sum += val;
                }
                double average = sum / size;
                
                System.out.println("Sum of elements: " + sum);
                System.out.println("Average of elements: " + average);
            } else {
                System.out.println("Invalid input. Please enter an integer.");
            }
        } finally {
            scanner.close();
        }
    }
}
