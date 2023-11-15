package io.seoLeir.blog.entity;

import io.seoLeir.blog.entity.keys.PublicationFileId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "publications_files")
public class PublicationFile {

    @EmbeddedId
    private PublicationFileId id;

    @MapsId("publicationUuid")
    @JoinColumn(name = "publication_uuid", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @MapsId("fileUuid")
    @JoinColumn(name = "file_name", referencedColumnName = "name")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    public PublicationFile(Publication publication, File file) {
        this.publication = publication;
        this.file = file;
        this.id = new PublicationFileId(publication.getId(), file.getFilename());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationFile that = (PublicationFile) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
