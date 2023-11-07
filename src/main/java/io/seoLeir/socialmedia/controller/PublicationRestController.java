package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
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
    * The method is used to create a publication. Approved
    */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicationCreateResponseDto createPublication(
            @RequestBody @Validated PublicationCreateRequestDto dto, Principal principal){
        return new PublicationCreateResponseDto(publicationService.createPublication(dto, principal.getName()));
    }


    /**
     * Approved
     */
    @DeleteMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@PathVariable("publication-uuid") UUID publicationUuid, Principal principal){
        publicationService.delete(publicationUuid, principal.getName());
    }

    /**
     * Approved
     */
    @PatchMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePublication(@PathVariable("publication-uuid") @NotBlank UUID publicationUuid,
                                  @RequestBody PublicationUpdateRequestDto dto,
                                  @RequestHeader("Authorization") String authorizationToken){
        publicationService.update(dto, publicationUuid, authorizationToken.substring(7));
    }

    /**
    * Approved
    */
    @GetMapping("/{publication-uuid}")
    public PublicationGetResponseDto getUserConcretePublication(@PathVariable("publication-uuid") UUID publicationUuid){
        return publicationService.getConcretePublication(publicationUuid)
                .orElseThrow(() -> new PublicationNotFoundException(
                        "Publication with id:" + publicationUuid.toString() + " not found",
                        HttpStatusCode.valueOf(404)));
    }

    @GetMapping
    public PageResponseDto<PublicationGetResponseDto> defaultFeed(PageRequestDto pageRequestDto) {
        return publicationService.getUserFeedByNew( null, pageRequestDto);
    }

    @GetMapping("/rated{range}")
    public PageResponseDto<PublicationGetResponseDto> feedWithRangeByNewPost(PageRequestDto pageRequestDto,
                                                                             @PathVariable("range") Integer rangeFilter) {
        return publicationService.getUserFeedByNew(rangeFilter, pageRequestDto);
    }

    @GetMapping("/top/{period}")
    public PageResponseDto<PublicationGetResponseDto> feedWithPeriodByPopularPost(PageRequestDto pageRequestDto,
                                                                                  @PathVariable("period") PeriodType period) {
        return publicationService.getUserFeedByTop(period, pageRequestDto);
    }

    @GetMapping("/search")
    public PageResponseDto<PublicationGetResponseDto> searchPosts(PageRequestDto pageRequestDto,
                                                                  @RequestParam("q") String searchFilterText,
                                                                  @RequestParam("order") OrderType orderType) {
        return publicationService.getPublicationsWithSearchFilter(searchFilterText, orderType, pageRequestDto);
    }
}
