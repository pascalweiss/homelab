package pw.examples.service_with_tracing;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    /**
     * Creates a RestTemplate bean. This ensures that the tracing headers are propagated to the downstream service.
     * @param restTemplateBuilder The RestTemplateBuilder to use, which has been auto-configured by Spring Boot.
     * @return the resulting RestTemplate
     */
    @Bean
    public RestTemplate restTemplateBuilder(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }
}
