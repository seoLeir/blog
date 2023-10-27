package io.seoLeir.socialmedia.entity;

import io.seoLeir.socialmedia.entity.keys.PublicationCommentsLikeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications_comments_likes")
@EntityListeners(AuditingEntityListener.class)
public class PublicationCommentLike implements BaseEntity<PublicationCommentsLikeId>{

    @EmbeddedId
    private PublicationCommentsLikeId id;

    @MapsId("userUuid")
    @JoinColumn(name = "user_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("publicationCommentUuid")
    @JoinColumn(name = "publication_comment_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PublicationComment publicationComment;


    @Column(name = "action_datetime", nullable = false)
    @CreatedDate
    private Instant likeDateTime;

    @Column(name = "is_like")
    private Boolean isLike;

    public PublicationCommentLike(User user, PublicationComment publicationComment) {
        this.user = user;
        this.publicationComment = publicationComment;
        this.id = new PublicationCommentsLikeId(user.getId(), publicationComment.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationCommentLike that = (PublicationCommentLike) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
