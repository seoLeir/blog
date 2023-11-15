package io.seoLeir.blog.repository;

import io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.blog.entity.PublicationComment;
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

    @Query("select new io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto(" +
            "pc.id, " +
            "pc.user.id, " +
            "pc.publication.id, " +
            "pc.parentPublicationComment.id, " +
            "pc.commentMessage, " +
            "pc.createdAt, " +
            "SUM(CASE WHEN pcl.isLike = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN pcl.isLike = false THEN 1 ELSE 0 END), " +
            "exists(select 1 from PublicationCommentLike plcml " +
            "where plcml.id.userUuid = :userUuid and plcml.id.publicationCommentUuid = pc.id))" +
            "from PublicationComment pc " +
            "left join PublicationCommentLike pcl on pc.id = pcl.id.publicationCommentUuid " +
            "where pc.publication.id = :publicationUuid " +
            "group by pc.id")
    Page<PublicationCommentGetResponseDto> getPublicationComments(@Param("publicationUuid") UUID publicationUuid,
                                                                  @Param("userUuid") UUID userUuid,
                                                                  Pageable pageable);

    @Query("select new io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto(" +
            "pc.id, " +
            "pc.user.id, " +
            "pc.publication.id, " +
            "pc.parentPublicationComment.id, " +
            "pc.commentMessage, " +
            "pc.createdAt, " +
            "SUM(CASE WHEN pcl.isLike = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN pcl.isLike = false THEN 1 ELSE 0 END), " +
            "exists(select 1 from PublicationCommentLike plcml " +
            "where plcml.id.userUuid = :currentUser and plcml.id.publicationCommentUuid = pc.id))" +
            "from PublicationComment pc " +
            "left join PublicationCommentLike pcl on pc.id = pcl.id.publicationCommentUuid " +
            "where pc.user.id = :userUuid " +
            "group by pc.id")
    Page<PublicationCommentGetResponseDto> getUserComments(@Param("userUuid") UUID uuid,
                                                           @Param("currentUser") UUID currentUser,
                                                           Pageable pageable);

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
