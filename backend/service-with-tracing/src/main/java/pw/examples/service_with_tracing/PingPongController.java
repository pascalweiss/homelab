package pw.examples.service_with_tracing;

import io.opentelemetry.api.trace.Span;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PingPongController {

    @Value("${pw.homelab.pong.port}")
    private String pongPort;


    private final RestTemplate restTemplate;

    /**
     * Receives a ping from the user and requests a pong from the pong service.
     */
    @GetMapping("/ping")
    public String receiveFromUser() {
        logInfo("received ping, sending pong");
        var response = restTemplate.getForObject(getPongUrl(), String.class);
        return "ping " + response;
    }

    /**
     * Receives a pong from the ping service.
     */
    @GetMapping("/pong")
    public String receiveRand() {
        logInfo("Received pong, returning pong");
        return "pong";
    }

    private String getPongUrl() {
        return "http://localhost:%s/pong".formatted(pongPort);
    }

    private void logInfo(String msg) {
        var traceId = Span.current().getSpanContext().getTraceId();
        log.info("traceId: {}, msg: {}", traceId, msg);
    }

}
