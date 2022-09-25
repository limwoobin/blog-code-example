package com.example.lockexample;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ActiveProfiles("test")
@Testcontainers
public class MySQLTestContainer {
    private static final String MYSQL_DOCKER_IMAGE = "mysql:5.7.22";

    @Container
    static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>(DockerImageName.parse(MYSQL_DOCKER_IMAGE))
            .withDatabaseName("test");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        System.out.println("#### " + MYSQL_CONTAINER.getJdbcUrl());
        System.out.println("#### " + MYSQL_CONTAINER.getUsername());
        System.out.println("#### " + MYSQL_CONTAINER.getPassword());
        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
    }
}
