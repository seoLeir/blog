package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationCommentLike;
import io.seoLeir.socialmedia.entity.keys.PublicationCommentsLikeId;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PublicationCommentLikeRepository extends JpaRepository<PublicationCommentLike, PublicationCommentsLikeId> {
}
