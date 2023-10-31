package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.socialmedia.entity.PublicationLike;
import io.seoLeir.socialmedia.entity.keys.PublicationLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublicationLikeRepository extends JpaRepository<PublicationLike, PublicationLikeId> {

    @Query("select new io.seoLeir.socialmedia.dto.publication.PublicationLikeAndDislikeDto( " +
            "pl.id.publicationUuid, " +
            "sum(CASE WHEN pl.isLike = true THEN 1 ELSE 0 END), " +
            "sum(CASE WHEN pl.isLike = false THEN 1 ELSE 0 END) ) " +
            "from PublicationLike pl " +
            "where pl.id.publicationUuid = :ids " +
            "group by pl.id.publicationUuid")
    PublicationLikeAndDislikeDto getPublicationLikesAndDislikesByPublicationUuid(@Param("ids")UUID publicationUuid);

    @Query("select exists(" +
            "select 1 " +
            " from PublicationLike pl " +
            "where pl.id.userUuid = :userUuid " +
            " and pl.id.publicationUuid = :publicationUuid)")
    boolean isUserLikedThePost(@Param("userUuid") UUID userUuid, @Param("publicationUuid") UUID publicationUuid);
}
