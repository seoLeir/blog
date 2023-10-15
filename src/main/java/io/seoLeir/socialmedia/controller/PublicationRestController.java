package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationUpdateRequestDto;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFound;
import io.seoLeir.socialmedia.service.PublicationService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

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
    @ResponseStatus(HttpStatus.OK)
    public void updatePublication(@PathVariable("publication-uuid") @NotBlank UUID publicationUuid,
                                  @RequestBody PublicationUpdateRequestDto dto){
        publicationService.update(dto, publicationUuid);
    }

    @GetMapping("/{publication-uuid}")
    public PublicationGetResponseDto getUserConcretePublication(@PathVariable("publication-uuid") UUID publicationUuid){
        return publicationService.getPublication(publicationUuid)
                .orElseThrow(() -> new PublicationNotFound(
                        "Publication with id:" + publicationUuid.toString() + " not found",
                        HttpStatusCode.valueOf(404)));
    }

    @GetMapping("/{publication-uuid}/comments")
    public PageResponseDto<PublicationUserCommentsDto> getPublicationAllComments(
            @PathVariable("publication-uuid")UUID id){

    }

}
