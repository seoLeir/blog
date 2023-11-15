package io.seoLeir.blog.entity;


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
@Table(name = "files")
@EntityListeners(AuditingEntityListener.class)
public class File{

    @Id
    @Column(name = "name")
    private UUID filename;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loaded_by", referencedColumnName = "id")
    private User user;

    @CreatedDate
    @Column(name = "loaded_time", nullable = false)
    private Instant loadedTime;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "file")
    private List<PublicationFile> publicationFiles;


    public File(UUID name, String realName, String mimeType, User loadedBy) {
        this.filename = name;
        this.realName = realName;
        this.mimeType = mimeType;
        this.user = loadedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(filename, file.filename) && Objects.equals(mimeType, file.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, mimeType);
    }
}
