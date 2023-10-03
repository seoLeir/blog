package io.seoLeir.socialmedia.repository;

import io.seoLeir.socialmedia.entity.Subscription;
import io.seoLeir.socialmedia.entity.keys.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

//    @Modifying
//    @Query("update Subscription s set " +
//            "s.")
//    void updateFriendshipStatus(Subscription subscription);
}
