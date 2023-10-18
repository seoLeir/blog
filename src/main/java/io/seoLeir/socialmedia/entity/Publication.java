package io.seoLeir.socialmedia.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications")
@EntityListeners(AuditingEntityListener.class)
@FieldNameConstants
public class Publication implements BaseEntity<UUID> {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "publication_text")
    private String text;

    @JoinColumn(name = "publisher_username")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "time_to_read_in_minutes")
    private Integer timeToReadInMinutes;

    @Column(name = "is_draft")
    private Boolean isDraft;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "is_edited")
    private Boolean isEdited;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "modified_at")
    @LastModifiedDate
    private Instant modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publication")
    private List<PublicationFile> publicationFiles;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PublicationComment> publicationComments;

    public Publication(UUID id, String tittle, String text, User user, Integer timeToReadInMinutes) {
        this.id = id;
        this.tittle = tittle;
        this.text = text;
        this.user = user;
        this.timeToReadInMinutes = timeToReadInMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publication that = (Publication) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Publication{" +
                "id=" + id +
                ", tittle='" + tittle + '\'' +
                ", text='" + text + '\'' +
                ", isPublished=" + isPublished +
                ", viewCount=" + viewCount +
                ", timeToReadInMinutes=" + timeToReadInMinutes +
                ", isDraft=" + isDraft +
                ", isHidden=" + isHidden +
                ", isEdited=" + isEdited +
                ", createdDate=" + createdDate +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
