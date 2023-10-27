package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.repository.UserBookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserBookmarkService {
    private final UserBookmarkRepository userBookmarkRepository;

    public List<UUID> getUseAllBookmarkedPublicationsUuid(UUID userUuid){
        return userBookmarkRepository.getAllPublicationsByUserUsername(userUuid);
    }

    public long getUserAllPublicationsCount(String username){
//        return userBookmarkRepository.getUserAllBookmarkedPublicationsCount(username);
//        TODO implement this method 26.10.2023
        return 0;
    }

    public List<String> getPublicationThatUsersBookmarked(UUID publicationUuid){
        return userBookmarkRepository.getAllUsernameByPublicationUuid(publicationUuid);
    }
}
