package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/publication")
@RequiredArgsConstructor
public class PublicationRestController {
    private final PublicationService publicationService;

    @ResponseStatus(HttpStatus.CREATED)
    public PublicationResponseDto createPublication(PublicationCreateRequestDto dto,
                                                    @AuthenticationPrincipal Principal principal){
        String publisherName = principal.getName();
        publicationService.createPublication(dto, publisherName);
        return null;
    }
}
