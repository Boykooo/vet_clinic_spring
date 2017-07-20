package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {
        "app.rest", "services", "app.filters",
        "security", "app.configs", "config"
})
@EntityScan(value = {"entities", "mongoEntities"})
@EnableJpaRepositories("repository")
@EnableMongoRepositories("repository")
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}