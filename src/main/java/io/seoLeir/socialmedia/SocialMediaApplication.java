package io.seoLeir.socialmedia;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import java.time.Instant;


@SpringBootApplication
@ConfigurationPropertiesScan
@EnableJpaAuditing
public class SocialMediaApplication {

	public static void main(String[] args) {
		System.out.println(Instant.now());
		SpringApplication.run(SocialMediaApplication.class, args);
	}
}
