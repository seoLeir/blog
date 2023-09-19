package io.seoLeir.socialmedia.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
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
@Table(name = "files")
@EntityListeners(AuditingEntityListener.class)
public class File implements BaseEntity<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "name")
    private UUID id;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "file_extension", nullable = false)
    private String fileExtension;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @CreatedDate
    @Column(name = "loaded_time", nullable = false)
    private Instant loadedTime;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Publication> publications = new ArrayList<>();

    public File(UUID name, String realName, String fileExtension, String mimeType) {
        this.id = name;
        this.realName = realName;
        this.fileExtension = fileExtension;
        this.mimeType = mimeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        File file = (File) o;
        return Objects.equals(id, file.id) && Objects.equals(fileExtension, file.fileExtension) && Objects.equals(mimeType, file.mimeType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fileExtension, mimeType);
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", fileExtension='" + fileExtension + '\'' +
                ", mimeType='" + mimeType + '\'' +
                ", loadedTime=" + loadedTime +
                '}';
    }
}
