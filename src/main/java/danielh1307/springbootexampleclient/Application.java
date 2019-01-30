package danielh1307.springbootexampleclient;

import io.swagger.client.model.AddNewFilmRequest;
import io.swagger.client.model.Film;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Base64;

public class Application {

    public static void main(String[] args) {
        // create a new Film
        AddNewFilmRequest addNewFilmRequest = new AddNewFilmRequest().title("Neuer Titel").year(2000);
        System.out.println("New AddNewFilmRequest created ...");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<AddNewFilmRequest> addNewFilmRequestWithHeaders = new HttpEntity<>(addNewFilmRequest, createHeaders("user", "appl_123"));

        System.out.println("Calling service ...");
        ResponseEntity<Film> response = restTemplate.postForEntity("http://localhost:8080/api/films", addNewFilmRequestWithHeaders, Film.class);
        System.out.println("Response code is: [" + response.getStatusCode().toString() + "]");
        System.out.println("Response string is: [" + response.getBody() + "]");
        System.out.println("Title is: [" + response.getBody().getTitle() + "]");
    }

    private static HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.getEncoder().encode(
                    auth.getBytes(Charset.forName("US-ASCII")) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }
}
