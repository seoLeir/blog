package io.seoLeir.socialmedia.entity.keys;


import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PublicationLikeId implements Serializable {

    @Serial
    private static final long serialVersionUID = 79879165641645L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_uuid")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_uuid")
    private Publication publication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationLikeId that = (PublicationLikeId) o;
        return Objects.equals(user, that.user) && Objects.equals(publication, that.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, publication);
    }

    @Override
    public String toString() {
        return "PublicationLikeId{" +
                "user=" + user +
                ", publication=" + publication +
                '}';
    }
}
