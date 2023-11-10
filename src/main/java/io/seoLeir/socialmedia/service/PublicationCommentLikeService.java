package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.PublicationComment;
import io.seoLeir.socialmedia.entity.PublicationCommentLike;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.comment.CommentNotFoundException;
import io.seoLeir.socialmedia.repository.PublicationCommentLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationCommentLikeService {
    private final PublicationCommentLikeRepository commentLikeRepository;

    @Transactional
    public void create(User user, PublicationComment publicationComment, Boolean isLike){
        PublicationCommentLike publicationCommentLike = new PublicationCommentLike(user, publicationComment, isLike);
        if (!commentLikeRepository.existsById(publicationCommentLike.getId())){
            commentLikeRepository.save(publicationCommentLike);
        }
    }

    @Transactional
    public void remove(User user, PublicationComment publicationComment) {
        PublicationCommentLike publicationCommentLike = new PublicationCommentLike(user, publicationComment);
        if(!commentLikeRepository.existsById(publicationCommentLike.getId())){
            throw new CommentNotFoundException("Publication comment not found", HttpStatusCode.valueOf(404));
        }
        commentLikeRepository.delete(publicationCommentLike);
    }

    @Transactional
    public void update(User user, PublicationComment publicationComment, Boolean like){
        PublicationCommentLike publicationCommentLike = new PublicationCommentLike(user, publicationComment);
        if(!commentLikeRepository.existsById(publicationCommentLike.getId())){
            throw new CommentNotFoundException("Publication comment not found", HttpStatusCode.valueOf(404));
        }
        if (commentLikeRepository.getReferenceById(publicationCommentLike.getId()).getIsLike() != like){
            commentLikeRepository.updateLikeStatus(like, publicationCommentLike.getId());
        }
    }
}
