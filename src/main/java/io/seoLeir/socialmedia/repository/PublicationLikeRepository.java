package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.publication.LikesAndDislikesDto;
import io.seoLeir.socialmedia.entity.PublicationLike;
import io.seoLeir.socialmedia.entity.keys.PublicationLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PublicationLikeRepository extends JpaRepository<PublicationLike, PublicationLikeId> {

    @Query("select " +
            "    pl.id.publicationUuid AS publicationUuid," +
            "    sum(CASE WHEN pl.isLike = true THEN 1 ELSE 0 END) AS likes, " +
            "    sum(CASE WHEN pl.isLike = false THEN 1 ELSE 0 END) AS dislikes " +
            "from PublicationLike pl " +
            "where pl.id.publicationUuid = :ids " +
            "group by pl.id.publicationUuid")
    LikesAndDislikesDto getPublicationLikesAndDislikesByPublicationUuid(@Param("ids")UUID publicationUuid);

    @Query("select exists(" +
            "select 1 " +
            " from PublicationLike pl " +
            "where pl.id.userUuid = :userUuid " +
            " and pl.id.publicationUuid = :publicationUuid)")
    boolean isUserLikedThePost(@Param("userUuid") UUID userUuid, @Param("publicationUuid") UUID publicationUuid);
}
