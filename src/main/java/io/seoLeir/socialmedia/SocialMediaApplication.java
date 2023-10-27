package io.seoLeir.socialmedia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
@EnableJpaAuditing
@EnableMethodSecurity
public class SocialMediaApplication {
	public static void main(String[] args) {
		SpringApplication.run(SocialMediaApplication.class, args);
	}
}
