package io.seoLeir.socialmedia.entity.keys;

import io.seoLeir.socialmedia.entity.File;
import io.seoLeir.socialmedia.entity.Publication;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PublicationFileId implements Serializable {

    @Serial
    private static final long serialVersionUID = 9879230311230254L;

    @JoinColumn(name = "publication_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @JoinColumn(name = "file_uuid")
    @ManyToOne(fetch = FetchType.LAZY)
    private File file;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationFileId that = (PublicationFileId) o;
        return Objects.equals(publication, that.publication) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publication, file);
    }
}
