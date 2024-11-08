package pw.examples.service_with_tracing;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PongController {

    @Value("${pw.homelab.pong.port}")
    private String pongPort;

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ping")
    public String receiveFromUser() {
        var response = restTemplate.getForObject("http://localhost:%s/pong".formatted(pongPort), String.class);
        return "ping " + response;
    }

    @GetMapping("/pong")
    public String receiveRand() {
        return "pong";
    }

}
