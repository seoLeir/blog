package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.*;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFoundException;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.service.PublicationService;
import io.seoLeir.socialmedia.service.UserBookmarkService;
import io.seoLeir.socialmedia.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class PublicationRestController {
    private final PublicationService publicationService;
    private final UserBookmarkService userBookmarkService;
    private final UserService userService;

    /**
     * The method is used to create a publication. Approved
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublicationCreateResponseDto createPublication(
            @Valid @RequestBody PublicationCreateRequestDto dto, Principal principal) {
        return new PublicationCreateResponseDto(publicationService.createPublication(dto, principal.getName()));
    }


    /**
     * Approved
     */
    @DeleteMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePublication(@PathVariable("publication-uuid") UUID publicationUuid, Principal principal) {
        publicationService.delete(publicationUuid, principal.getName());
    }

    /**
     * Approved
     */
    @PatchMapping("/{publication-uuid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updatePublication(@PathVariable("publication-uuid") @NotBlank UUID publicationUuid,
                                  @Valid @RequestBody PublicationUpdateRequestDto dto,
                                  @RequestHeader("Authorization") String authorizationToken) {
        publicationService.update(dto, publicationUuid, authorizationToken.substring(7));
    }

    /**
     * Approved
     */
    @GetMapping("/{publication-uuid}")
    public PublicationGetResponseDto getUserConcretePublication(@PathVariable("publication-uuid") UUID publicationUuid) {
        return publicationService.getConcretePublication(publicationUuid)
                .orElseThrow(() -> new PublicationNotFoundException(
                        "Publication with id:" + publicationUuid.toString() + " not found",
                        HttpStatusCode.valueOf(404)));
    }

    /**
     * Accepted
     */
    @GetMapping("/feed")
    public PageResponseDto<PublicationGetResponseDto> defaultFeed(@Valid @RequestBody PageRequestDto pageRequestDto) {
        return publicationService.getUserFeedByNew("rated0", pageRequestDto);
    }


    /**
     * Accepted
     */
    @GetMapping("/feed/{range}")
    public PageResponseDto<PublicationGetResponseDto> feedWithRangeByNewPost(@Valid @RequestBody PageRequestDto pageRequestDto,
                                                                             @PathVariable(value = "range") String rangeFilter) {
        return publicationService.getUserFeedByNew(rangeFilter, pageRequestDto);
    }

    /**
     * Accepted
     */
    @GetMapping("/feed/top/{period}")
    public PageResponseDto<PublicationGetResponseDto> feedWithPeriodByPopularPost(@Valid @RequestBody PageRequestDto pageRequestDto,
                                                                                  @PathVariable("period") String period) {
        return publicationService.getUserFeedByTop(period, pageRequestDto);
    }

    /**
     * Accepted
     */
    @GetMapping("/search")
    public PageResponseDto<PublicationGetResponseDto> searchPosts(@Valid @RequestBody PageRequestDto pageRequestDto,
                                                                  @RequestParam("q") String searchFilterText,
                                                                  @RequestParam("order") OrderType orderType) {
        return publicationService.getPublicationsWithSearchFilter(searchFilterText, orderType, pageRequestDto);
    }


    /**
    * Accepted
    */
    @PostMapping("/{publication-uuid}/bookmarks")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBookmark(@PathVariable("publication-uuid") UUID publicationUuid, Principal principal) {
        Publication publication = publicationService.getPublication(publicationUuid);
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        userBookmarkService.create(publication, user);
    }

    /**
     * Accepted
     */
    @DeleteMapping("/{publication-uuid}/bookmarks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookmark(@PathVariable("publication-uuid") UUID publicationUuid, Principal principal) {
        Publication publication = publicationService.getPublication(publicationUuid);
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        userBookmarkService.delete(publication, user);
    }

    /**
    * Accepted
    */
    @GetMapping("/{publication-uuid}/bookmarks")
    public PageResponseDto<String> getPublicationBookmarks(@PathVariable("publication-uuid") UUID publicationUuid,
                                                           @Valid @RequestBody PageRequestDto requestDto) {
        return userBookmarkService.getBookmarks(publicationUuid, requestDto);
    }


    /**
    * Accepted
    */
    @PostMapping("/{publication-uuid}/likes")
    public ResponseEntity<?> likeOrDislikeOrUpdatePublication(@PathVariable("publication-uuid") UUID publicationUuid,
                                                              @Valid @RequestBody PublicationActionWithStatusRequest dto,
                                                              Principal principal) {
        return publicationService.likeOrDislikeOrUpdatePublication(publicationUuid, principal.getName(), dto);
    }

    /**
     * Accepted
     */
    @GetMapping("/{publication-uuid}/likes")
    public PageResponseDto<PublicationLikesAndDislikesResponseDto> getPublicationLikes(@PathVariable("publication-uuid") UUID publicationUuid,
                                                                                       @Valid @RequestBody PageRequestDto requestDto) {
        return publicationService.getLikesAndDislikes(publicationUuid, requestDto);
    }

}
