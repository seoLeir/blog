package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.dto.comment.CommentLikeAndDislikeDto;
import io.seoLeir.socialmedia.entity.PublicationCommentLike;
import io.seoLeir.socialmedia.entity.keys.PublicationCommentsLikeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublicationCommentLikeRepository extends JpaRepository<PublicationCommentLike, PublicationCommentsLikeId>{
    @Query("select exists(" +
            "select 1 from PublicationCommentLike pcl " +
            "where pcl.id.userUuid = :userUuid " +
            "and pcl.id.publicationCommentUuid = :commentUuid)")
    boolean isUserLikedComment(@Param("userUuid") UUID userUuid, @Param("commentUuid") UUID commentUuid);

    @Query("select new io.seoLeir.socialmedia.dto.comment.CommentLikeAndDislikeDto(" +
            "pcl.id.publicationCommentUuid, " +
            "SUM(CASE WHEN pcl.isLike = true THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN pcl.isLike = false THEN 1 ELSE 0 END) )"+
            "from PublicationCommentLike pcl " +
            "where pcl.id.publicationCommentUuid = :commentUuid " +
            "group by pcl.id.publicationCommentUuid")
    CommentLikeAndDislikeDto getPublicationCommentLikesAndDislikes(@Param("commentUuid") UUID commentUuid);

}
