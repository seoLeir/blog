package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.publication.*;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFoundException;
import io.seoLeir.socialmedia.service.PublicationService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class PublicationRestController {
    private final PublicationService publicationService;

    /**
    * The method is used to create a publication
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicationCreateResponseDto createPublication(
            @RequestBody @Validated PublicationCreateRequestDto dto, Principal principal){
        return new PublicationCreateResponseDto(publicationService.createPublication(dto, principal.getName()));
    }


    @DeleteMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@PathVariable("publication-uuid") UUID publicationUuid, Principal principal){
        publicationService.delete(publicationUuid, principal.getName());
    }

    @PatchMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePublication(@PathVariable("publication-uuid") @NotBlank UUID publicationUuid,
                                  @RequestBody PublicationUpdateRequestDto dto,
                                  @RequestHeader("Authorization") String authorizationToken){
        publicationService.update(dto, publicationUuid, authorizationToken.substring(7));
    }

    @GetMapping("/{publication-uuid}")
    public PublicationGetResponseDto getUserConcretePublication(@PathVariable("publication-uuid") UUID publicationUuid){
        return publicationService.getPublicationResponseDto(publicationUuid)
                .orElseThrow(() -> new PublicationNotFoundException(
                        "Publication with id:" + publicationUuid.toString() + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
