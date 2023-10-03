package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.CommentLike;
import io.seoLeir.socialmedia.entity.keys.CommentLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLike, CommentLikeId> {
}
