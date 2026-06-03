import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Exercise22_FileWriting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter string to write to file: ");
            String data = scanner.nextLine();
            
            try (FileWriter writer = new FileWriter("output.txt")) {
                writer.write(data);
                System.out.println("Data successfully written to output.txt");
            } catch (IOException e) {
                System.out.println("An error occurred during file writing: " + e.getMessage());
            }
        } finally {
            scanner.close();
        }
    }
}
