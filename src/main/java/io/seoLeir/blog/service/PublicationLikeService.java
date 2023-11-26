package io.seoLeir.blog.service;

import io.seoLeir.blog.dto.page.PageRequestDto;
import io.seoLeir.blog.dto.page.PageResponseDto;
import io.seoLeir.blog.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.blog.dto.publication.PublicationLikesAndDislikesResponseDto;
import io.seoLeir.blog.entity.Publication;
import io.seoLeir.blog.entity.PublicationLike;
import io.seoLeir.blog.entity.User;
import io.seoLeir.blog.exception.publication.PublicationNotFoundException;
import io.seoLeir.blog.repository.PublicationLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
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

    @Transactional
    public void create(User user,Publication publication, Boolean isLike){
        PublicationLike publicationLike = new PublicationLike(user, publication, isLike);
        if (!publicationLikeRepository.existsById(publicationLike.getId())){
            publicationLikeRepository.save(publicationLike);
        }
    }

    @Transactional
    public void update(User user, Publication publication, Boolean newStatus){
        PublicationLike publicationLike = new PublicationLike(user, publication);
        if(!publicationLikeRepository.existsById(publicationLike.getId())){
            throw new PublicationNotFoundException("Publication not found", HttpStatusCode.valueOf(404));
        }
        if(publicationLikeRepository.getReferenceById(publicationLike.getId()).getIsLike() != newStatus){
            publicationLikeRepository.updateStatusById(newStatus, publicationLike.getId());
        }
    }

    @Transactional(readOnly = true)
    public PageResponseDto<PublicationLikesAndDislikesResponseDto> getLikesAndDislikes(UUID publicationUuid, PageRequestDto requestDto) {
        Pageable pageable = PageRequestDto.toPageable(requestDto);
        Page<PublicationLikesAndDislikesResponseDto> publicationLikesAndDislikes =
                publicationLikeRepository.getPublicationLikesAndDislikes(publicationUuid, pageable);
        return PageResponseDto.of(publicationLikesAndDislikes);
    }

    @Transactional
    public void delete(User user, Publication publication) {
        PublicationLike publicationLike = new PublicationLike(user, publication);
        if(!publicationLikeRepository.existsById(publicationLike.getId())){
            throw new PublicationNotFoundException("Publication not found", HttpStatusCode.valueOf(404));
        }
        publicationLikeRepository.delete(publicationLike);
    }
}
