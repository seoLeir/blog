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
        return userBookmarkRepository.getAllPublicationsByUserUsername(userUuid);
    }

    @Transactional
    public long getUserAllPublicationsCount(String username){
        return userBookmarkRepository.getUserBookmarkedPublicationsCount(username);
    }

    @Transactional
    public Long getPublicationBookmarkedCount(UUID publicationUuid){
        return userBookmarkRepository.getPublicationBookmarksCount(publicationUuid);
    }

    @Transactional(readOnly = true)
    public boolean isUserBookmarkedPublication(UUID userUuid, UUID publicationUuid){
        return userBookmarkRepository.isUserBookmarkedPublication(userUuid, publicationUuid);
    }
}
