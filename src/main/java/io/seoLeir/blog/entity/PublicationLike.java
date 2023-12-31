package io.seoLeir.blog.entity;

import io.seoLeir.blog.entity.keys.PublicationLikeId;
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
@Table(name = "publications_likes")
@EntityListeners(AuditingEntityListener.class)
public class PublicationLike implements BaseEntity<PublicationLikeId>{

    @EmbeddedId
    private PublicationLikeId id;

    @MapsId("userUuid")
    @JoinColumn(name = "user_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("publicationUuid")
    @JoinColumn(name = "publication_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @Column(name = "action_datetime", nullable = false)
    @CreatedDate
    private Instant likeDateTime;

    @Column(name = "is_like")
    private Boolean isLike;

    public PublicationLike(User user, Publication publication, Boolean isLike) {
        this.id = new PublicationLikeId(user.getId(), publication.getId());
        this.user = user;
        this.publication = publication;
        this.isLike = isLike;
    }

    public PublicationLike(User user, Publication publication) {
        this.id = new PublicationLikeId(user.getId(), publication.getId());
        this.user = user;
        this.publication = publication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationLike that = (PublicationLike) o;
        return Objects.equals(user, that.user) && Objects.equals(publication, that.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, publication);
    }
}
