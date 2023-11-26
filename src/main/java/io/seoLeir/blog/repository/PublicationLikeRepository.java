package io.seoLeir.blog.repository;

import io.seoLeir.blog.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.blog.dto.publication.PublicationLikesAndDislikesResponseDto;
import io.seoLeir.blog.entity.PublicationLike;
import io.seoLeir.blog.entity.keys.PublicationLikeId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublicationLikeRepository extends JpaRepository<PublicationLike, PublicationLikeId> {

    @Query("select new io.seoLeir.blog.dto.publication.PublicationLikeAndDislikeDto( " +
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

    @Query("""
    select new io.seoLeir.blog.dto.publication.PublicationLikesAndDislikesResponseDto(
        pl.user.username, pl.isLike
    ) from PublicationLike pl where pl.id.publicationUuid = :publicationId
    """)
    Page<PublicationLikesAndDislikesResponseDto> getPublicationLikesAndDislikes(@Param("publicationId")UUID publicationUUid,
                                                                                Pageable pageable);

    @Modifying(flushAutomatically = true)
    @Query("update PublicationLike pl set pl.isLike = :status where pl.id = :id")
    void updateStatusById(@Param("status") Boolean status, @Param("id") PublicationLikeId publicationLikeId);
}
