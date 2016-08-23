package io.pivotal;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

import static com.codahale.metrics.MetricRegistry.name;

@RestController
@SpringBootApplication
public class ActuatorDemoApplication {

    private final Timer responses;

    private final Random delay = new SecureRandom();

    @Autowired
    ActuatorDemoApplication(MetricRegistry metricRegistry) {
        this.responses = metricRegistry.timer(name(ActuatorDemoApplication.class, "responses"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ActuatorDemoApplication.class, args);
    }

    @Bean(initMethod = "start")
    JmxReporter jmxReporter(MetricRegistry metricRegistry) {
        return JmxReporter.forRegistry(metricRegistry)
            .build();
    }

    @GetMapping("/test")
    Map<String, Long> test() throws InterruptedException {
        try (Timer.Context context = this.responses.time()) {
            long delay = this.delay.nextInt(1000);
            Thread.sleep(delay);
            return Collections.singletonMap("delay", delay);
        }
    }

}
