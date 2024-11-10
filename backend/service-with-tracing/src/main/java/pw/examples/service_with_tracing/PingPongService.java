package pw.examples.service_with_tracing;

import io.opentelemetry.api.trace.Tracer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PingPongService {

    Tracer tracer;

    /**
     * returns pong with a custom trace
     */
    public String getPong() {
        tracer.spanBuilder("pong-service").startSpan().end();
        return "pong";
    }


    /**
     * returns ping
     */
    public String getPing() {
        return "ping";
    }
}
