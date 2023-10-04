package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface PublicationCommentRepository extends JpaRepository<PublicationComment, UUID> {
}
