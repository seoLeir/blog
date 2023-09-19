package io.seoLeir.socialmedia.entity;

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
@Table(name = "publication_likes")
@EntityListeners(AuditingEntityListener.class)
public class PublicationLike implements BaseEntity<PublicationLikeId>{

    @EmbeddedId
    private PublicationLikeId id;

    @Column(name = "like_datetime", nullable = false)
    @CreatedDate
    private Instant likeDateTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationLike that = (PublicationLike) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PublicationLike{" +
                "id=" + id +
                ", likeDateTime=" + likeDateTime +
                '}';
    }
}
