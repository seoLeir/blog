package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.socialmedia.repository.PublicationLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PublicationLikeService {
    private final PublicationLikeRepository publicationLikeRepository;

    @Transactional(readOnly = true)
    public PublicationLikeAndDislikeDto getPublicationLikesAndDislikesByPublicationUuid(UUID publicationUuid){
        return publicationLikeRepository.getPublicationLikesAndDislikesByPublicationUuid(publicationUuid);
    }

    @Transactional(readOnly = true)
    public boolean isUserLikedThePost(UUID userUuid, UUID publicationUuid){
        return publicationLikeRepository.isUserLikedThePost(userUuid, publicationUuid);
    }

}
