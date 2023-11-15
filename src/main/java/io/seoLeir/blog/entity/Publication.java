package io.seoLeir.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
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
@Table(name = "publications")
@EntityListeners(AuditingEntityListener.class)
@FieldNameConstants
public class Publication implements BaseEntity<UUID> {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "publication_text")
    private String text;

    @JoinColumn(name = "publisher_uuid", referencedColumnName = "id")
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

    @Column(name = "created_date")
    @CreationTimestamp
    private Instant createdDate;

    @Column(name = "modified_at")
    @LastModifiedDate
    private Instant modifiedAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publication", cascade = CascadeType.REMOVE)
    private List<PublicationFile> publicationFiles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publication", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<PublicationComment> publicationComments;

    public Publication(UUID id, String title, String text, User user, Long viewCount, Integer timeToReadInMinutes, Instant createdDate) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.user = user;
        this.viewCount = viewCount;
        this.timeToReadInMinutes = timeToReadInMinutes;
        this.createdDate = createdDate;
    }

    public Publication(UUID uuid, String header, String text, User user, int minutesToRead) {
        this.id = uuid;
        this.title = header;
        this.text = text;
        this.user = user;
        this.timeToReadInMinutes = minutesToRead;
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
                ", tittle='" + title + '\'' +
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
