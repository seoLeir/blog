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
@Table(name = "messages_optional_parameters")
public class MessageOptionalParameter implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "parameter_uuid")
    private Long id;

    @Column(name = "is_spam")
    private Boolean isSpam;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "not_delivered")
    private Boolean notDelivered;

    @Column(name = "is_postponed")
    private Boolean isPostponed;

    @Column(name = "is_edited")
    private Boolean isEdited;

    @OneToOne(fetch = FetchType.LAZY)
    private Message message;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageOptionalParameter that = (MessageOptionalParameter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MessageOptionalParameter{" +
                "id=" + id +
                ", isSpam=" + isSpam +
                ", isDeleted=" + isDeleted +
                ", notDelivered=" + notDelivered +
                ", isPostponed=" + isPostponed +
                ", isEdited=" + isEdited +
                '}';
    }
}
