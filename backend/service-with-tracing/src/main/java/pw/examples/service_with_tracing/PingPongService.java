package pw.examples.service_with_tracing;

import io.micrometer.tracing.annotation.NewSpan;
import io.opentelemetry.api.trace.Tracer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PingPongService {

    Tracer tracer;

    @NewSpan
    public String getPong() {
        tracer.spanBuilder("pong-service").startSpan().end();
        return "pong";
    }


    public String getPing() {
        return "ping";
    }
}
