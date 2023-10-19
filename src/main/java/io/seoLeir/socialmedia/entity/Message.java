package io.seoLeir.socialmedia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
@Table(name = "messages")
public class Message implements BaseEntity<UUID> {

    @Id
    private UUID id;

    @JoinColumn(name = "user_from", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userFrom;

    @JoinColumn(name = "user_to", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userTo;

    @Column(name = "sent_datetime")
    @CreatedDate
    private Instant sentDateTime;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "is_read")
    private Boolean isRead;

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

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "message_files",
            joinColumns = {@JoinColumn(name = "message_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "file_name")})
    private List<File> file = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Message parentMessage;

    public Message(UUID id, User userFrom, User userTo, String messageBody) {
        this.id = id;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.messageBody = messageBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) && Objects.equals(sentDateTime, message.sentDateTime) && Objects.equals(messageBody, message.messageBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sentDateTime, messageBody);
    }


}
