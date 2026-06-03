import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Exercise36_HTTPClientAPI {
    public static void main(String[] args) {
        String url = "https://jsonplaceholder.typicode.com/posts/1";
        System.out.println("Sending HTTP GET request to: " + url);
        
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
                
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("\nHTTP Response Status Code: " + response.statusCode());
            System.out.println("HTTP Response Body:");
            System.out.println(response.body());
        } catch (Exception e) {
            System.out.println("Exception occurred while making HTTP request: " + e.getMessage());
            System.out.println("Please check your internet connection.");
        }
    }
}
