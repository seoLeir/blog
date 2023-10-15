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
    private final PublicationService publicationService;

    public PageResponseDto<Publication> getUseAllBookmarkedPublicationsUuid(
            String username,
            PageRequestDto pageRequestDto){
        Pageable pageable = PageRequest.of(pageRequestDto.pageNumber(), pageRequestDto.pageSize(), pageRequestDto.sort());
        List<UUID> userAllBookmarkedPublications = userBookmarkRepository.getAllPublicationsByUserUsername(username);
        return publicationService.getAllUserBookmarkedPublication(userAllBookmarkedPublications, pageable);
    }

    public long getUserBookmarkedPublicationsCount(String username){

    }

    public List<String> getPublicationThatUsersBookmarked(UUID publicationUuid){
        return userBookmarkRepository.getAllUsernameByPublicationUuid(publicationUuid);
    }
}
