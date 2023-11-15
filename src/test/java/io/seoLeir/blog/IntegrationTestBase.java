package io.seoLeir.blog;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;



@SpringBootTest(classes = SocialMediaApplication.class)
public abstract class IntegrationTestBase {
    public static final PostgreSQLContainer<?> CONTAINER = new PostgreSQLContainer<>("postgres:15.0");

    @BeforeAll
    public static void runContainer(){
        CONTAINER.start();
    }

    @AfterAll
    public static void stopContainer(){
        CONTAINER.stop();
    }

    @DynamicPropertySource
    public static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", CONTAINER::getUsername);
        registry.add("spring.datasource.password", CONTAINER::getPassword);
    }
}
