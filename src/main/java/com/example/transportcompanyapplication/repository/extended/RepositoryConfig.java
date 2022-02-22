package com.example.transportcompanyapplication.repository.extended;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.example.transportcompanyapplication.repository"},
        repositoryBaseClass = ExtendedRepositoryImpl.class)
public class RepositoryConfig {
}
