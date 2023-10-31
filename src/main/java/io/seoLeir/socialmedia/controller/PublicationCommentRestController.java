package io.seoLeir.socialmedia.controller;


import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.service.PublicationCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
//        TODO 31.10.2023
    }

    @PostMapping
    public void updateComment(@PathVariable("publication-uuid") UUID id){
//        TODO 31.10.2023
    }
}
