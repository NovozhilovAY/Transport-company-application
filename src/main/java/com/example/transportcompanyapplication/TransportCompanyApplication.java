package com.example.transportcompanyapplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Transport company application", version = "1.0"))
public class TransportCompanyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransportCompanyApplication.class, args);
    }

}
