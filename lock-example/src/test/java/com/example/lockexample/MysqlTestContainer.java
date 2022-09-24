//package com.example.lockexample;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.testcontainers.containers.MySQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//
//@SpringBootTest
//@ActiveProfiles("test")
//@Testcontainers
//public class MysqlTestContainer {
//    private static final String MYSQL_DOCKER_IMAGE = "mysql:5.7.22";
//    @Container
//    private static final MySQLContainer<?> MYSQL_CONTAINER = new MySQLContainer<>(MYSQL_DOCKER_IMAGE);
//
//    @DynamicPropertySource
//    public static void properties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", MYSQL_CONTAINER::getJdbcUrl);
//        registry.add("spring.datasource.username", MYSQL_CONTAINER::getUsername);
//        registry.add("spring.datasource.password", MYSQL_CONTAINER::getPassword);
//    }
//}
