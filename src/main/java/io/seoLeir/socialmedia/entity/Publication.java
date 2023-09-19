package io.seoLeir.socialmedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
public class Publication implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "header", nullable = false)
    private String header;

    @Column(name = "publication_text")
    private String text;

    @JoinColumn(name = "publisher_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(name = "modified_at")
    @LastModifiedDate
    private Instant modifiedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parameters")
    private PublicationOptionalParameter publicationOptionalParameter;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "publication_files",
            joinColumns = {@JoinColumn(name = "publication_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "file_uuid")})
    private List<File> files = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY)
    private List<Comment> publicationComments = new ArrayList<>();

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
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", createdDate=" + createdDate +
                ", modifiedAt=" + modifiedAt +
                '}';
    }
}
