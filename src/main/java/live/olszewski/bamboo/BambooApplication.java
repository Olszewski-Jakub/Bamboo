package live.olszewski.bamboo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BambooApplication {

    public static void main(String[] args) {
        SpringApplication.run(BambooApplication.class, args);
    }
}
