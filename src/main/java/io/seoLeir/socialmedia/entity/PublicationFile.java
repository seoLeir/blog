package io.seoLeir.socialmedia.entity;

import io.seoLeir.socialmedia.entity.keys.PublicationFileId;
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
@Table(name = "publication_files")
public class PublicationFile {

    @EmbeddedId
    private PublicationFileId id;

    @MapsId("publicationUuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @MapsId("fileUuid")
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
        return Objects.equals(publication, that.publication) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, file);
    }
}
