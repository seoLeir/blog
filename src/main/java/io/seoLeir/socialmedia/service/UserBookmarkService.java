package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.entity.UserBookmark;
import io.seoLeir.socialmedia.exception.bookmark.UserBookmarkNotFound;
import io.seoLeir.socialmedia.repository.UserBookmarkRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserBookmarkService {
    private final UserBookmarkRepository userBookmarkRepository;

    @Transactional(readOnly = true)
    public List<UUID> getUseAllBookmarkedPublicationsUuid(UUID userUuid){
        return userBookmarkRepository.getAllPublicationsByUserUsername(userUuid);
    }

    @Transactional(readOnly = true)
    public long getUserAllPublicationsCount(String username){
        return userBookmarkRepository.getUserBookmarkedPublicationsCount(username);
    }

    @Transactional(readOnly = true)
    public Long getPublicationBookmarkedCount(UUID publicationUuid){
        return userBookmarkRepository.getPublicationBookmarksCount(publicationUuid);
    }

    @Transactional(readOnly = true)
    public boolean isUserBookmarkedPublication(UUID userUuid, UUID publicationUuid){
        return userBookmarkRepository.isUserBookmarkedPublication(userUuid, publicationUuid);
    }

    @Transactional
    public void create(Publication publication, User user) {
        UserBookmark userBookmark = new UserBookmark(user, publication);
        if (userBookmarkRepository.findById(userBookmark.getId()).isEmpty()){
            userBookmarkRepository.save(userBookmark);
        }
    }

    @Transactional
    public void delete(Publication publication, User user) {
        UserBookmark userBookmark = new UserBookmark(user, publication);
        if (!userBookmarkRepository.existsById(userBookmark.getId())){
            throw new UserBookmarkNotFound("User bookmark not found", HttpStatusCode.valueOf(404));
        }
        userBookmarkRepository.delete(userBookmark);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<String> getBookmarks(UUID publicationUuid, PageRequestDto pageRequestDto){
        Pageable pageable = PageRequestDto.toPageable(pageRequestDto);
        Page<String> publicationBookmarks = userBookmarkRepository.getPublicationBookmarkedUsernames(publicationUuid, pageable);
        return PageResponseDto.of(publicationBookmarks);
    }
}
