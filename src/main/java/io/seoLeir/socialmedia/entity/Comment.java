package io.seoLeir.socialmedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_comments")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "publication_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @JoinColumn(name = "parent_comment_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment parentComment;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE)
    private List<Comment> childComments;

    @Column(name = "comment_message")
    private String commentMessage;

    @CreatedDate
    @Column(name = "created_at")
    private Instant createdAt;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", publication=" + publication +
                ", commentMessage='" + commentMessage + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
