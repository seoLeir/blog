package io.seoLeir.socialmedia.entity;


import io.seoLeir.socialmedia.entity.keys.UserBookmarksId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users_bookmarks")
@EntityListeners(AuditingEntityListener.class)
public class UserBookmark implements BaseEntity<UserBookmarksId> {

    @EmbeddedId
    private UserBookmarksId id;

    @MapsId("userUsername")
    @JoinColumn(name = "user_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @MapsId("publicationUuid")
    @JoinColumn(name = "publication_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @Column(name = "bookmarked_date")
    @CreatedDate
    private LocalDate bookmarkedDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookmark that = (UserBookmark) o;
        return Objects.equals(user, that.user) && Objects.equals(publication, that.publication);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, publication);
    }
}

