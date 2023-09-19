package io.seoLeir.socialmedia.entity;

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
@Table(name = "publication_optional_parameters")
public class PublicationOptionalParameter implements BaseEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_published")
    private Boolean isPublished;

    @Column(name = "is_draft")
    private Boolean isDraft;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Column(name = "is_edited")
    private Boolean isEdited;

    @OneToOne(fetch = FetchType.LAZY)
    private Publication publication;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicationOptionalParameter that = (PublicationOptionalParameter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PublicationOptionalParameter{" +
                "id=" + id +
                ", isPublished=" + isPublished +
                ", isDraft=" + isDraft +
                ", isHidden=" + isHidden +
                ", isEdited=" + isEdited +
                '}';
    }
}
