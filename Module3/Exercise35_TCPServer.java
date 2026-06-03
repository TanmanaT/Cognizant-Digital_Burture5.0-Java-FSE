import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Exercise35_TCPServer {
    public static void main(String[] args) {
        int port = 5000;
        System.out.println("Starting TCP Server on port " + port + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening. Waiting for a client to connect...");
            
            try (Socket clientSocket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in))) {
                 
                System.out.println("Client connected from: " + clientSocket.getRemoteSocketAddress());
                out.println("Welcome to the TCP Chat Room! Type 'exit' to disconnect.");
                
                String clientMsg;
                // Reading client messages in a separate logic or simple loop
                while ((clientMsg = in.readLine()) != null) {
                    System.out.println("Client says: " + clientMsg);
                    if (clientMsg.equalsIgnoreCase("exit")) {
                        System.out.println("Client initiated disconnect.");
                        break;
                    }
                    // Echo response back
                    out.println("Server echo: " + clientMsg);
                }
            }
            System.out.println("Client disconnected. Connection closed.");
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
