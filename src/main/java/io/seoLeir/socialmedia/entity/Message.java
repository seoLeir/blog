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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @JoinColumn(name = "user_from", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userFrom;

    @JoinColumn(name = "user_to", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User userTo;

    @Column(name = "sent_datetime", nullable = false)
    @CreatedDate
    private Instant sentDateTime;

    @Column(name = "message_body", nullable = false)
    private String messageBody;

    @Column(name = "is_read")
    private Boolean isRead;

    @JoinColumn(name = "parameters")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private MessageOptionalParameter parameters;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "message_files",
            joinColumns = {@JoinColumn(name = "message_uuid")},
            inverseJoinColumns = {@JoinColumn(name = "file_uuid")})
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

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", sentDateTime=" + sentDateTime +
                ", messageBody='" + messageBody + '\'' +
                ", isRead=" + isRead +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
