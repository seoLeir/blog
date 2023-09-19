package io.seoLeir.socialmedia;

import io.seoLeir.socialmedia.entity.File;
import io.seoLeir.socialmedia.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class SocialMediaApplication {

	public static void main(String[] args) {
		System.out.println(Instant.now());
		SpringApplication.run(SocialMediaApplication.class, args);
	}
}
