package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.PublicationLike;
import io.seoLeir.socialmedia.entity.PublicationLikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationLikeRepository extends JpaRepository<PublicationLike, PublicationLikeId> {
}
