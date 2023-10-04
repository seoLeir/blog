package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationCommentLike;
import io.seoLeir.socialmedia.entity.keys.PublicationCommentsLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentLikeRepository extends JpaRepository<PublicationCommentLike, PublicationCommentsLikeId> {
}
