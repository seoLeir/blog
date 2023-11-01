package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserSubscriptionService {
    private final SubscriptionRepository subscriptionRepository;
    private final UserService userService;

    @Transactional
    public Long getUserFollowersCount(Principal principal){
//        TODO 02.11.2023
        return null;
    }

    @Transactional
    public Long getUserFollowingCount(Principal principal){
//        TODO 02.11.2023
        return null;
    }

    @Transactional
    public Page<String> getUserFollowers(Principal principal, PageRequestDto dto){
//        TODO 02.11.2023
        return null;
    }
    @Transactional
    public Page<String> getUserFollowing(Principal principal, PageRequestDto dto){
//        TODO 02.11.2023
        return null;
    }
}
