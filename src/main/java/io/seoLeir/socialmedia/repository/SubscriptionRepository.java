package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Subscription;
import io.seoLeir.socialmedia.entity.keys.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    @Query("select s.id, s.isMutual, s.subscriptionDate from Subscription s where s.id.subscriber = :id")
    UUID getAllBySubscriberId(@Param("id") UUID uuid);

}
