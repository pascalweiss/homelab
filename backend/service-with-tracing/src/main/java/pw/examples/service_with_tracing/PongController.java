package pw.examples.service_with_tracing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PongController {

    @Value("${pw.homelab.pong.port}")
    private String pongPort;


    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ping")
    public String receiveFromUser() {
        log.info("Received ping, requesting pong");
        var response = restTemplate.getForObject("http://localhost:%s/pong".formatted(pongPort), String.class);
        log.info("Received pong, returning ping pong");
        return "ping " + response;
    }

    @GetMapping("/pong")
    public String receiveRand() {
        log.info("Received pong, returning pong");
        return "pong";
    }

}
