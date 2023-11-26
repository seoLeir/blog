package io.seoLeir.blog.repository;

import io.seoLeir.blog.dto.comment.CommentLikeAndDislikeDto;
import io.seoLeir.blog.entity.PublicationCommentLike;
import io.seoLeir.blog.entity.keys.PublicationCommentsLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublicationCommentLikeRepository extends JpaRepository<PublicationCommentLike, PublicationCommentsLikeId>{
    @Query("select exists(" +
            "select 1 from PublicationCommentLike pcl " +
            "where pcl.id.userUuid = :userUuid " +
            "and pcl.id.publicationCommentUuid = :commentUuid)")
    boolean isUserLikedOrDislikedComment(@Param("userUuid") UUID userUuid, @Param("commentUuid") UUID commentUuid);

    @Query("select new io.seoLeir.blog.dto.comment.CommentLikeAndDislikeDto(" +
            "pcl.id.publicationCommentUuid, " +
            "SUM(CASE WHEN pcl.isLike = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN pcl.isLike = false THEN 1 ELSE 0 END) )"+
            "from PublicationCommentLike pcl " +
            "where pcl.id.publicationCommentUuid = :commentUuid " +
            "group by pcl.id.publicationCommentUuid")
    CommentLikeAndDislikeDto getPublicationCommentLikesAndDislikes(@Param("commentUuid") UUID commentUuid);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("update PublicationCommentLike pcl set pcl.isLike = :status where pcl.id = :id")
    void updateLikeStatus(@Param("status") Boolean status, @Param("id") PublicationCommentsLikeId id);
}
