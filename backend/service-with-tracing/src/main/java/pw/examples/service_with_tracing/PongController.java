package pw.examples.service_with_tracing;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PongController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ping")
    public String ping() {
        restTemplate.getForObject("http://localhost:8080/pong", String.class);
        return "ping";
    }

}
