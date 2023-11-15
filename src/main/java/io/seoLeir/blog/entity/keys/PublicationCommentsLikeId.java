package io.seoLeir.blog.entity.keys;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PublicationCommentsLikeId implements Serializable {
    @Serial
    private static final long serialVersionUID = 487863211454645L;

    @Column(name = "user_uuid")
    private UUID userUuid;

    @Column(name = "publication_comment_uuid")
    private UUID publicationCommentUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationCommentsLikeId that = (PublicationCommentsLikeId) o;
        return Objects.equals(userUuid, that.userUuid) && Objects.equals(publicationCommentUuid, that.publicationCommentUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUuid, publicationCommentUuid);
    }

    @Override
    public String toString() {
        return "PublicationCommentsLikeId{" +
                "userUuid=" + userUuid +
                ", publicationCommentUuid=" + publicationCommentUuid +
                '}';
    }
}
