package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.comment.PublicationUserCommentsDto;
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

    public List<PublicationComment> publicationCommentsByPublicationUuid(UUID publicationUuid){
        return commentRepository.getAllByPublication(publicationUuid);
    }

    public List<PublicationComment> publicationCommentsByUserUuid(UUID userUuid){
        return commentRepository.getAllByUser(userUuid);
    }

    public PageResponseDto<PublicationUserCommentsDto> publicationCommentPageResponse(
            UUID userUuid, PageRequestDto dto){
        Pageable pageable = PageRequest.of(dto.pageNumber(), dto.pageSize(), dto.sort());
        List<PublicationUserCommentsDto> userCommentsDto = commentRepository.getAllByUserUsername(userUuid, pageable).getContent().stream()
                .map(publicationComment -> new PublicationUserCommentsDto(publicationComment.getId(),
                        publicationComment.getUser().getId(),
                        publicationComment.getPublication().getId(),
                        publicationComment.getParentPublicationComment().getId(),
                        publicationComment.getCommentMessage(),
                        publicationComment.getCreatedAt()))
                .toList();
        Page<PublicationComment> page = commentRepository.getAllByUserUsername(userUuid, pageable);
        return new PageResponseDto<>(
                userCommentsDto,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber() + 1,
                page.isLast(),
                page.isFirst());
    }
}
