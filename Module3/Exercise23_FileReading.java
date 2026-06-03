import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Exercise23_FileReading {
    public static void main(String[] args) {
        System.out.println("Reading contents from output.txt:");
        try (BufferedReader reader = new BufferedReader(new FileReader("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Could not read file output.txt. Ensure that Exercise 22 has run first. Error: " + e.getMessage());
        }
    }
}
