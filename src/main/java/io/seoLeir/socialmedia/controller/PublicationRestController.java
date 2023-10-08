package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationUpdateRequestDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFound;
import io.seoLeir.socialmedia.service.PublicationService;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public PublicationCreateResponseDto createPublication(@RequestBody PublicationCreateRequestDto dto,
                                                          @AuthenticationPrincipal Principal principal){
        return new PublicationCreateResponseDto(publicationService.createPublication(dto, principal.getName()));
    }


    @DeleteMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@NotBlank @PathVariable("publication-uuid") UUID publicationUuid){
        publicationService.delete(publicationUuid);
    }

    @PatchMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePublication(@NotBlank @PathVariable("publication-uuid") UUID publicationUuid,
                                  @RequestBody PublicationUpdateRequestDto dto){
        publicationService.update(dto, publicationUuid);
    }

    @GetMapping("/{username}/all")
    public PageResponseDto<Publication> getUserAllPublication(@PathVariable("username") String username,
                                                              @RequestBody PageRequestDto dto,
                                                              @RequestParam("text") String textToSearch){
        return publicationService.getAllUserPublications(username, dto, textToSearch);
    }

    @GetMapping("/{username}/{publication-uuid}")
    public PublicationGetResponseDto getUserConcretePublication(
            @PathVariable("username") String username,
            @PathVariable("publication-uuid") UUID publicationUuid){
        return publicationService.getPublication(publicationUuid, username)
                .orElseThrow(() -> new PublicationNotFound(
                        "Publication with id:" + publicationUuid.toString() + " not found",
                        HttpStatusCode.valueOf(404)));
    }
}
