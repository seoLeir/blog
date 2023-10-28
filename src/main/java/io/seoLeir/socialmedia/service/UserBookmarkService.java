package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.repository.UserBookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserBookmarkService {
    private final UserBookmarkRepository userBookmarkRepository;

    @Transactional
    public List<UUID> getUseAllBookmarkedPublicationsUuid(UUID userUuid){
        log.info("method getUseAllBookmarkedPublicationsUuid() was called with parameter: {}", userUuid);
        return userBookmarkRepository.getAllPublicationsByUserUsername(userUuid);
    }

    @Transactional
    public long getUserAllPublicationsCount(String username){
//        return userBookmarkRepository.getUserAllBookmarkedPublicationsCount(username);
//        TODO implement this method 26.10.2023
        return 0;
    }

    @Transactional
    public List<String> getPublicationThatUsersBookmarked(UUID publicationUuid){
        return userBookmarkRepository.getAllUsernameByPublicationUuid(publicationUuid);
    }

    @Transactional(readOnly = true)
    public boolean isUserBookmarkedPublication(UUID userUuid, UUID publicationUuid){
        return userBookmarkRepository.isUserBookmarkedPublication(userUuid, publicationUuid);
    }
}
