package io.seoLeir.socialmedia.entity.keys;

import io.seoLeir.socialmedia.entity.User;
import jakarta.persistence.*;
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
public class SubscriptionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 989523136466448L;

    @JoinColumn(name = "subscriber_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscriber;

    @JoinColumn(name = "follower_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId that = (SubscriptionId) o;
        return Objects.equals(subscriber, that.subscriber) && Objects.equals(targetUser, that.targetUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriber, targetUser);
    }

    @Override
    public String toString() {
        return "SubscriptionId{" +
                "subscriber=" + subscriber +
                ", follower=" + targetUser +
                '}';
    }
}
