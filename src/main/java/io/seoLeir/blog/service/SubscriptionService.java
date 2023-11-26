package io.seoLeir.blog.service;

import io.seoLeir.blog.entity.User;
import io.seoLeir.blog.exception.user.UserNotFountException;
import io.seoLeir.blog.repository.SubscriptionRepository;
import io.seoLeir.blog.dto.page.PageRequestDto;
import io.seoLeir.blog.dto.page.PageResponseDto;
import io.seoLeir.blog.entity.Subscription;
import io.seoLeir.blog.exception.subscription.SubscriptionNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Transactional(readOnly = true)
    public Long getUserFollowersCount(UUID userUuid){
        return subscriptionRepository.getUserFollowersCount(userUuid);
    }

    @Transactional(readOnly = true)
    public Boolean isUserFollowed(UUID subscriber, UUID target){
        return subscriptionRepository.isSubscriptionExists(subscriber, target);
    }

    @Transactional
    public void subscribe(String currentUser, String userToFollow){
        User current = userService.findByUsername(currentUser)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        User target = userService.findByUsername(userToFollow)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        Subscription subscription;
        if (subscriptionRepository.isSubscriptionExists(target.getId(), current.getId())) {
            subscription = new Subscription(current, target, true);
            if (!subscriptionRepository.getSubscriptionStatus(target.getId(), current.getId())){
                subscriptionRepository.updateSubscriptionStatus(target.getId(), current.getId(), true);
            }
        }else{
            subscription = new Subscription(current, target, false);
        }
        subscriptionRepository.save(subscription);
    }

    @Transactional
    public void unsubscribe(String currentUser, String userToFollow){
        User current = userService.findByUsername(currentUser)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        User target = userService.findByUsername(userToFollow)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        if (!subscriptionRepository.isSubscriptionExists(current.getId(), target.getId()))
            throw new SubscriptionNotFoundException("Subscription not found", HttpStatusCode.valueOf(404));
        if(subscriptionRepository.getSubscriptionStatus(target.getId(), current.getId()))
           subscriptionRepository.updateSubscriptionStatus(target.getId(), current.getId(), false);
        Subscription subscription = new Subscription(current, target);
        subscriptionRepository.delete(subscription);
    }

    @Transactional(readOnly = true)
    public Long getUserFollowingCount(UUID userUuid){
        return subscriptionRepository.getUserFollowingCount(userUuid);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<String> getUserFollowers(String username, PageRequestDto dto){
        Pageable pageable = PageRequestDto.toPageable(dto);
        UUID userUuid = userService.getUserUuidFromUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        Page<Subscription> userFollowers = subscriptionRepository.getUserFollowers(userUuid, pageable);
        List<String> responseContentList = userFollowers.getContent().stream()
                .map(subscription -> String.valueOf(subscription.getSubscriberUser().getUsername()))
                .toList();
        return PageResponseDto.of(userFollowers, responseContentList);
    }
    @Transactional(readOnly = true)
    public PageResponseDto<String> getUserFollowing(String  username, PageRequestDto dto){
        Pageable pageable = PageRequestDto.toPageable(dto);
        UUID userUuid = userService.getUserUuidFromUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        Page<Subscription> userFollowing = subscriptionRepository.getUserFollowing(userUuid, pageable);
        List<String> responseContentList = userFollowing.getContent().stream()
                .map(subscription -> String.valueOf(subscription.getTargetUser().getUsername()))
                .toList();
        return PageResponseDto.of(userFollowing, responseContentList);
    }

}
