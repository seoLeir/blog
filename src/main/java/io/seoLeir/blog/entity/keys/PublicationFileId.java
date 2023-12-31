package io.seoLeir.blog.entity.keys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PublicationFileId implements Serializable {

    @Serial
    private static final long serialVersionUID = 798165981645L;

    @Column(name = "publication_uuid")
    private UUID publicationUuid;

    @Column(name = "file_name")
    private UUID fileUuid;

    @Override
    public String toString() {
        return "PublicationFileId{" +
                "publicationUuid=" + publicationUuid +
                ", fileUuid=" + fileUuid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationFileId that = (PublicationFileId) o;
        return Objects.equals(publicationUuid, that.publicationUuid) && Objects.equals(fileUuid, that.fileUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationUuid, fileUuid);
    }
}
