package com.example.transportcompanyapplication;

import com.example.transportcompanyapplication.security.SecurityConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableScheduling
@OpenAPIDefinition(info = @Info(title = "Transport company application", version = "1.0"))
public class TransportCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportCompanyApplication.class, args);
    }

}
