package io.seoLeir.socialmedia.controller;


import io.seoLeir.socialmedia.dto.comment.CommentCreateRequestDto;
import io.seoLeir.socialmedia.dto.comment.CommentCreateResponseDto;
import io.seoLeir.socialmedia.dto.comment.CommentUpdateDto;
import io.seoLeir.socialmedia.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.service.PublicationCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/{publication-uuid}/comments")
@RequiredArgsConstructor
public class PublicationCommentRestController {
    private final PublicationCommentService publicationCommentService;

    @GetMapping
    public PageResponseDto<PublicationCommentGetResponseDto> getPublicationAllComments(
            @PathVariable("publication-uuid") UUID publicationUuid, @RequestBody PageRequestDto dto){
        return publicationCommentService.getPublicationComments(publicationUuid, dto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentCreateResponseDto createComment(@PathVariable("publication-uuid") UUID publicationUuid,
                                                  @RequestBody CommentCreateRequestDto dto,
                                                  @RequestParam(value = "parentComment", required = false) UUID parentCommentUuid,
                                                  Principal principal){
        return publicationCommentService.createComment(publicationUuid, dto, parentCommentUuid, principal);
    }

    @PatchMapping
    public void updateComment(@PathVariable("publication-uuid") UUID publicationUuid, @RequestBody CommentUpdateDto dto){
        publicationCommentService.updateComment(publicationUuid, dto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable("publication-uuid") UUID publicationUuid, @RequestBody UUID commentUuid){
        publicationCommentService.delete(publicationUuid, commentUuid);
    }

}
