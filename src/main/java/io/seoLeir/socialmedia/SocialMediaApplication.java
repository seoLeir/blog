package io.seoLeir.socialmedia;


import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.Roles;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;


@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
@EnableJpaAuditing
public class SocialMediaApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SocialMediaApplication.class, args);
		PasswordEncoder passwordEncoder = context.getBean(PasswordEncoder.class);
		User user = new User(UUID.fromString("0000ee7f-2d4c-47aa-9459-6e2f8bf22d59"), "admin", "mirzayevmiralim28@gmail.com", passwordEncoder.encode("123"), Roles.ROLE_ADMIN);
		Publication publication = new Publication(
				UUID.randomUUID(), "some-header", "some-text", user, 18);
		PublicationRepository publicationRepository = context.getBean(PublicationRepository.class);
		Publication save = publicationRepository.save(publication);
		System.out.println(save);
	}
}
