package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface PublicationCommentRepository extends JpaRepository<PublicationComment, UUID> {
}
