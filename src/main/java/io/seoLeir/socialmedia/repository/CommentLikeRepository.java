package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommentLikeRepository extends JpaRepository<CommentLike, UUID> {
}
