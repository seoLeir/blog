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
@Table(name = "publication_comments_likes")
@EntityListeners(AuditingEntityListener.class)
public class PublicationCommentLike implements BaseEntity<PublicationCommentsLikeId>{

    @EmbeddedId
    private PublicationCommentsLikeId id;

    @Column(name = "like_datetime", nullable = false)
    @CreatedDate
    private Instant likeDateTime;

    public PublicationCommentLike(PublicationCommentsLikeId id) {
        this.id = id;
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