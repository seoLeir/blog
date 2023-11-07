package io.seoLeir.socialmedia;


import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.FeedDto;
import io.seoLeir.socialmedia.dto.publication.PeriodType;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import io.seoLeir.socialmedia.service.PublicationService;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.time.*;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.*;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.YEARS;


@SpringBootApplication
@EnableJpaRepositories
@ConfigurationPropertiesScan
@EnableJpaAuditing
@EnableMethodSecurity
public class SocialMediaApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SocialMediaApplication.class, args);
		PublicationService publicationService = context.getBean(PublicationService.class);
		PageRequestDto page = new PageRequestDto(0, 20, Sort.unsorted());
		PageResponseDto<PublicationGetResponseDto> userFeedByTop = publicationService.getUserFeedByTop(PeriodType.alltime, page);
		userFeedByTop.getContent().forEach(System.out::println);


		PublicationRepository publicationRepository = context.getBean(PublicationRepository.class);
		/*Instant start = LocalDateTime.now().minus(10, YEARS).toInstant(ZoneOffset.UTC);
		Page<FeedDto> topPublicationsByPeriod = publicationRepository.getTopPublicationsByPeriod(start, Instant.now(), Pageable.unpaged());
		topPublicationsByPeriod.getContent().forEach(feedDto ->
			System.out.println(feedDto.getPublicationUuid() + " : " + feedDto.getScore())
		);*/

		/*publicationRepository.getTopPublicationsAllTime(Pageable.unpaged()).getContent().forEach(feedDto -> {
			System.out.println(feedDto.getPublicationUuid() + " : " + feedDto.getScore());
		});*/


	}
}
