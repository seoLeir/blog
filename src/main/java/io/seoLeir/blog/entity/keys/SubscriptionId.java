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
public class SubscriptionId implements Serializable {

    @Serial
    private static final long serialVersionUID = 989523136466448L;

    @Column(name = "subscriber_id")
    private UUID subscriberUserUuid;

    @Column(name = "target_user")
    private UUID targetUserUuid;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId that = (SubscriptionId) o;
        return Objects.equals(subscriberUserUuid, that.subscriberUserUuid) && Objects.equals(targetUserUuid, that.targetUserUuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subscriberUserUuid, targetUserUuid);
    }

    @Override
    public String toString() {
        return "SubscriptionId{" +
                "subscriberUserUuid=" + subscriberUserUuid +
                ", targetUserUuid=" + targetUserUuid +
                '}';
    }
}
