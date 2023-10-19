package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsWithLikesDto;
import io.seoLeir.socialmedia.entity.PublicationComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;


public interface PublicationCommentRepository extends JpaRepository<PublicationComment, UUID>,
        JpaSpecificationExecutor<PublicationComment> {
    @Query("select p from PublicationComment p where p.publication.id = :uuid")
    List<PublicationComment> getAllByPublication(@Param("uuid") UUID publicationUuid);

    @Query("select count(p.id) from PublicationComment p where p.user.id = :uuid")
    Long getAllByUser(@Param("uuid") UUID userUuid);

    @Query(value = "select p.id, p.user.id, p.publication.id, p.parentPublicationComment.id, p.commentMessage, p.createdAt from PublicationComment p where p.user.id = :id order by p.createdAt desc",
            countQuery = "select count(p.id) from PublicationComment p")
    Page<PublicationUserCommentsDto> getAllByUserUsername(@Param("id") UUID userUuid, Pageable pageable);

//    Page<PublicationUserCommentsWithLikesDto> getAllCommentsByPublication();

}
