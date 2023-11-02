package io.seoLeir.socialmedia.entity;

import io.seoLeir.socialmedia.entity.keys.SubscriptionId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
@EntityListeners(AuditingEntityListener.class)
public class Subscription implements BaseEntity<SubscriptionId>{

    @EmbeddedId
    private SubscriptionId id;

    @MapsId("subscriberUserUuid")
    @JoinColumn(name = "subscriber_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User subscriberUser;

    @MapsId("targetUserUuid")
    @JoinColumn(name = "target_user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User targetUser;

    @Column(name = "subscription_datetime")
    @CreatedDate
    private Instant subscriptionDate;

    @Column(name = "is_mutual")
    private boolean isMutual;

    public Subscription(User subscriberUser, User targetUser) {
        this.subscriberUser = subscriberUser;
        this.targetUser = targetUser;
        this.id = new SubscriptionId(subscriberUser.getId(), targetUser.getId());
    }

    public Subscription(User subscriberUser, User targetUser, Boolean isMutual) {
        this.subscriberUser = subscriberUser;
        this.targetUser = targetUser;
        this.isMutual = isMutual;
        this.id = new SubscriptionId(subscriberUser.getId(), targetUser.getId());
    }
}
