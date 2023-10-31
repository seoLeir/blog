package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
import io.seoLeir.socialmedia.dto.comment.PublicationCommentWithLikesAndDislikes;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.entity.PublicationComment;
import io.seoLeir.socialmedia.repository.PublicationCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationCommentService {
    private final PublicationCommentRepository commentRepository;

    public long publicationCommentsByUserUuid(UUID userUuid){
        return commentRepository.getAllByUser(userUuid);
    }

    public PageResponseDto<PublicationUserCommentsDto> publicationCommentPageResponse(UUID userUuid, PageRequestDto dto) {
        Pageable pageable = PageRequest.of(dto.pageNumber(), dto.pageSize(), dto.sort());
        Page<PublicationUserCommentsDto> page = commentRepository.getAllByUserUsername(userUuid, pageable);
        return PageResponseDto.of(page);
    }

    public PageResponseDto<PublicationCommentWithLikesAndDislikes> publicationCommentWithLikesPageResponse(){
        return null;
    }
}
