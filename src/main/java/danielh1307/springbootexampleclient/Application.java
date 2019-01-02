package danielh1307.springbootexampleclient;

import io.swagger.client.model.AddNewFilmRequest;
import io.swagger.client.model.Film;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class Application {

    public static void main(String[] args) {
        // create a new Film
        AddNewFilmRequest addNewFilmRequest = new AddNewFilmRequest().title("Neuer Titel").year(2000);
        System.out.println("New AddNewFilmRequest created ...");

        RestTemplate restTemplate = new RestTemplate();

        System.out.println("Calling service ...");
        ResponseEntity<Film> response = restTemplate.postForEntity("http://localhost:8080/api/films", addNewFilmRequest, Film.class);
        System.out.println("Response code is: [" + response.getStatusCode().toString() + "]");
        System.out.println("Response string is: [" + response.getBody() + "]");
        System.out.println("Title is: [" + response.getBody().getTitle() + "]");
    }
}
