package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Subscription;
import io.seoLeir.socialmedia.entity.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {
}
