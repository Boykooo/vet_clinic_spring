package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackageClasses = UserController.class)
@EntityScan("entities")
@EnableJpaRepositories("repository")
public class SpringApp extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}