package io.seoLeir.blog.controller;


import io.seoLeir.blog.dto.comment.*;
import io.seoLeir.socialmedia.dto.comment.*;
import io.seoLeir.blog.dto.page.PageRequestDto;
import io.seoLeir.blog.dto.page.PageResponseDto;
import io.seoLeir.blog.dto.publication.PublicationActionWithStatusRequest;
import io.seoLeir.blog.service.PublicationCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/{publication-uuid}/comments")
@RequiredArgsConstructor
public class PublicationCommentRestController {
    private final PublicationCommentService publicationCommentService;

    /**
    * Approved
    */
    @GetMapping
    public PageResponseDto<PublicationCommentGetResponseDto> getPublicationAllComments(
            @PathVariable("publication-uuid") UUID publicationUuid,
            @Valid @RequestBody PageRequestDto dto,
            Principal principal){
        return publicationCommentService.getPublicationComments(publicationUuid, dto, principal.getName());
    }

    /**
     * Approved
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentCreateResponseDto createComment(@PathVariable("publication-uuid") UUID publicationUuid,
                                                  @Valid @RequestBody CommentCreateRequestDto dto,
                                                  @RequestParam(value = "parentComment", required = false) UUID parentCommentUuid,
                                                  Principal principal){
        return publicationCommentService.createComment(publicationUuid, dto, parentCommentUuid, principal.getName());
    }

    /**
     * Approved
     */
    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateComment(@PathVariable("publication-uuid") UUID publicationUuid,
                              @Valid @RequestBody CommentUpdateDto dto){
        publicationCommentService.updateComment(publicationUuid, dto);
    }

    /**
     * Approved
     */
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("publication-uuid") UUID publicationUuid,
                              @Valid @RequestBody CommentDeleteDto dto){
        publicationCommentService.deleteComment(publicationUuid, dto.commentUuid());
    }

    /**
    * Done implementing
    */
    @PostMapping("/likes")
    public ResponseEntity<?> likeOrDislikeComment(@PathVariable("publication-uuid") UUID commentUuid,
                                                  @Valid @RequestBody PublicationActionWithStatusRequest dto,
                                                  Principal principal) {
        return publicationCommentService.likeOrDislikeOrUpdateComment(commentUuid, principal.getName(), dto);
    }
}
