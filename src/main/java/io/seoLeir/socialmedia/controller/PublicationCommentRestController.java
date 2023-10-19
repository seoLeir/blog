package io.seoLeir.socialmedia.controller;


import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.service.PublicationCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/{publication-uuid}/comments")
@RequiredArgsConstructor
public class PublicationCommentRestController {
    private final PublicationCommentService publicationCommentService;

    @GetMapping
    public PageResponseDto<PublicationUserCommentsDto> getPublicationAllComments(
            @PathVariable("publication-uuid") UUID id){
        return null;
    }

}
