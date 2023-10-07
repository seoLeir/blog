package io.seoLeir.socialmedia.entity.keys;

import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class UserBookmarksId implements Serializable {

    @Serial
    private static final long serialVersionUID = 79131210545448L;

    @Column(name = "user_username")
    private String userUsername;

    @Column(name = "publication_uuid")
    private UUID publicationUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookmarksId that = (UserBookmarksId) o;
        return Objects.equals(userUsername, that.userUsername) && Objects.equals(publicationUuid, that.publicationUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userUsername, publicationUuid);
    }

    @Override
    public String toString() {
        return "UserBookmarksId{" +
                "userUsername='" + userUsername + '\'' +
                ", publicationUuid=" + publicationUuid +
                '}';
    }
}