package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.service.PublicationService;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class PublicationRestController {
    private final PublicationService publicationService;
    private final JwtTokenUtils jwtTokenUtils;

    /**
    * The method is used to create a publication
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicationCreateResponseDto createPublication(@RequestBody PublicationCreateRequestDto dto,
                                                          @AuthenticationPrincipal Principal principal){
        UUID publicationUuid = publicationService.createPublication(dto, principal.getName());
        return new PublicationCreateResponseDto(publicationUuid);
    }


    @GetMapping("/current/all")
    public PageResponseDto<Publication> getCurrentUserAllPublications(
            @AuthenticationPrincipal Principal principal,
            @RequestBody PageRequestDto dto){
        return publicationService.getAllUserPublications(principal.getName(), dto);
    }


    @GetMapping("/current/{publication-uuid}")
    public PublicationGetResponseDto getCurrentUserCertainPublication(
            @PathVariable("publication-uuid") UUID publicationUuid){
        return publicationService.getPublication(publicationUuid)
                .orElseThrow();
    }




}
