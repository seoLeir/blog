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

    @Column(name = "subscription_datetime")
    @CreatedDate
    private Instant subscriptionDate;

    @Column(name = "is_mutual")
    private boolean isMutual;

    public Subscription(SubscriptionId id) {
        this.id = id;
    }
}
