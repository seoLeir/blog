package io.seoLeir.socialmedia.entity.keys;

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

    @Column(name = "publication_uuid")
    private UUID publicationUuid;

    @Column(name = "file_uuid")
    private UUID fileUuid;

    @Override
    public String toString() {
        return "PublicationFileId{" +
                "publicationUuid=" + publicationUuid +
                ", fileUuid=" + fileUuid +
                '}';
    }
}
