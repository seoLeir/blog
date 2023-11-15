package io.seoLeir.blog.repository;

import io.seoLeir.blog.entity.Subscription;
import io.seoLeir.blog.entity.keys.SubscriptionId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    @Query("select count(s.id.targetUserUuid) from Subscription s where s.id.subscriberUserUuid = :id")
    Long getUserFollowingCount(@Param("id") UUID uuid);

    @Query("select count(s.id.subscriberUserUuid) from Subscription s where s.id.targetUserUuid = :id")
    Long getUserFollowersCount(@Param("id") UUID uuid);

    @Modifying(flushAutomatically = true)
    @Query("update Subscription s set s.isMutual = :status where s.subscriberUser.id = :subscriber and s.targetUser.id = :target")
    void updateSubscriptionStatus(@Param("subscriber") UUID subscriber, @Param("target") UUID target, @Param("status") Boolean status);

    @Query("select s from Subscription s where s.id.targetUserUuid = :userUuid order by s.subscriptionDate desc")
    Page<Subscription> getUserFollowers(@Param("userUuid") UUID userUuid, Pageable pageable);

    @Query("select s from Subscription s where s.id.subscriberUserUuid = :userUuid order by s.subscriptionDate desc")
    Page<Subscription> getUserFollowing(@Param("userUuid") UUID userUuid, Pageable pageable);

    @Query("select (count(s) > 0) from Subscription s " +
            "where s.id.subscriberUserUuid = :subscriberUuid and s.id.targetUserUuid = :targetUuid")
    boolean isSubscriptionExists(@Param("subscriberUuid") UUID subscriber, @Param("targetUuid") UUID target);

    @Query("select s.isMutual from Subscription s where s.id.subscriberUserUuid = :subscriberUuid and s.id.targetUserUuid = :targetUuid")
    boolean getSubscriptionStatus(@Param("subscriberUuid") UUID subscriber, @Param("targetUuid") UUID target);



}
