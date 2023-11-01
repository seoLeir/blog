package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.socialmedia.entity.PublicationComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface PublicationCommentRepository extends JpaRepository<PublicationComment, UUID>,
        JpaSpecificationExecutor<PublicationComment> {

    @Query("select count(p.id) from PublicationComment p where p.user.id = :uuid")
    Long getAllByUser(@Param("uuid") UUID userUuid);

    @Query(value = "select pc " +
            "from PublicationComment pc " +
            "where pc.publication.id in (:publicationUuid)",
            countQuery = "select count(pc.id) from PublicationComment pc where pc.publication.id = :publicationUuid")
    Page<PublicationComment> getPublicationCommentsAndItsLikesByPublicationUuid(@Param("publicationUuid") UUID publicationUuid,
                                                                                              Pageable pageable);

    @Query(value = "select pc " +
            "from PublicationComment pc " +
            "where pc.user.id = :userUuid",
            countQuery = "select pc from PublicationComment pc " +
                    "where pc.user.id = :userUuid ")
    Page<PublicationComment> getUserCommentsAndItsLikesByUserUuid(@Param("userUuid") UUID uuid, Pageable pageable);

    @Modifying(flushAutomatically = true)
    @Query("update PublicationComment pc set pc.commentMessage = :text where pc.id = :commentUuid")
    void updateCommentMessage(@Param("text") String textToUpdate, @Param("commentUuid") UUID commentUuid);

    @Query("select pc from PublicationComment pc where pc.id = :commentUuid")
    Optional<PublicationComment> getCommentByUserUuid(@Param("commentUuid") UUID commentUuid);

    @Modifying(flushAutomatically = true)
    @Query("delete from PublicationComment pc where pc.id = :id")
    void deleteByCommentUuid(@Param("id") UUID commentUuid);

    @Query("select pc from PublicationComment pc where pc.id = :commentUuid")
    Optional<PublicationComment> findPublicationCommentByUuid(@Param("commentUuid") UUID commentUuid);


}
