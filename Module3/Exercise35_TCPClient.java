import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Exercise35_TCPClient {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 5000;
        
        System.out.println("Connecting to TCP Server at " + host + ":" + port + "...");
        
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {
             
            System.out.println("Connected to server successfully!");
            
            // Read welcome message from server
            String welcomeMsg = in.readLine();
            System.out.println("Server Welcome: " + welcomeMsg);
            
            String userInput;
            System.out.print("Enter message to send (or 'exit' to quit): ");
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                
                // Read echo response from server
                String serverResponse = in.readLine();
                System.out.println("Received: " + serverResponse);
                System.out.print("Enter message to send: ");
            }
        } catch (IOException e) {
            System.out.println("Client Error: Could not connect to server on port " + port + ". Make sure the TCPServer is running first. (" + e.getMessage() + ")");
        }
    }
}
