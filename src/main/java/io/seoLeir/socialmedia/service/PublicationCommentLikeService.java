package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.comment.CommentLikeAndDislikeDto;
import io.seoLeir.socialmedia.repository.PublicationCommentLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationCommentLikeService {
    private final PublicationCommentLikeRepository commentLikeRepository;


    @Transactional
    public boolean isUserLiked(UUID userUuid, UUID commentUuid){
        return commentLikeRepository.isUserLikedComment(userUuid, commentUuid);
    }

    @Transactional
    public CommentLikeAndDislikeDto getCommentLikesAndDislikes(UUID commentUuid){
        return commentLikeRepository.getPublicationCommentLikesAndDislikes(commentUuid);
    }
}
