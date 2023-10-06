package io.seoLeir.socialmedia.entity.keys;

import io.seoLeir.socialmedia.entity.PublicationComment;
import io.seoLeir.socialmedia.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PublicationCommentsLikeId implements Serializable {
    @Serial
    private static final long serialVersionUID = 487863211454645L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_comment_uuid")
    private PublicationComment publicationComment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationCommentsLikeId that = (PublicationCommentsLikeId) o;
        return Objects.equals(user, that.user) && Objects.equals(publicationComment, that.publicationComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, publicationComment);
    }

    @Override
    public String toString() {
        return "CommentLikeId{" +
                "user=" + user +
                ", comment=" + publicationComment +
                '}';
    }
}
