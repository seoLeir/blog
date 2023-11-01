package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.comment.*;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.PublicationComment;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.comment.CommentNotFoundException;
import io.seoLeir.socialmedia.exception.publication.AccessDeniedException;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFoundException;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.mapper.PublicationCommentMapper;
import io.seoLeir.socialmedia.repository.PublicationCommentRepository;
import io.seoLeir.socialmedia.specifications.PublicationSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationCommentService {
    private final PublicationCommentRepository commentRepository;
    private PublicationCommentLikeService publicationCommentLikeService;
    private final PublicationCommentMapper commentMapper;
    private final PublicationService publicationService;
    private final UserService userService;

    @Lazy
    @Autowired
    private PublicationCommentService publicationCommentService;
    @Transactional
    public long publicationCommentsByUserUuid(UUID userUuid){
        return commentRepository.getAllByUser(userUuid);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<PublicationCommentGetResponseDto> getUserComments(UUID userUuid, PageRequestDto dto) {
        Pageable pageable = PageRequestDto.toPageable(dto);
        Page<PublicationComment> page = commentRepository.getUserCommentsAndItsLikesByUserUuid(userUuid, pageable);
        List<PublicationCommentGetResponseDto> responseDtos = publicationCommentService.dtoFromRawData(page.getContent());
        return PageResponseDto.of(page, responseDtos);
    }

    @Transactional
    public CommentCreateResponseDto createComment(UUID publicationUuid,
                                                  CommentCreateRequestDto dto,
                                                  UUID parentCommentUuid,
                                                  Principal principal){
        Publication publication = publicationService.getPublication(publicationUuid);
        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        PublicationComment publicationComment;
        if (parentCommentUuid == null){
            publicationComment = new PublicationComment(UUID.randomUUID(), user, publication, dto.commentText());
        }else {
            PublicationComment parentComment = publicationCommentService.findCommentByCommentUuid(parentCommentUuid)
                    .orElseThrow(() -> new CommentNotFoundException("Parent comment not found: " + parentCommentUuid, HttpStatusCode.valueOf(404)));
            publicationComment = new PublicationComment(UUID.randomUUID(), user, publication, parentComment, dto.commentText());
        }
        commentRepository.save(publicationComment);
        return new CommentCreateResponseDto(publicationComment.getId());
    }

    @Transactional
    public Optional<PublicationComment> findCommentByCommentUuid(UUID commentUuid){
        return commentRepository.findPublicationCommentByUuid(commentUuid);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<PublicationCommentGetResponseDto> getPublicationComments(UUID publicationUuid, PageRequestDto dto){
        Pageable pageable = PageRequestDto.toPageable(dto);
        Page<PublicationComment> page = commentRepository.getPublicationCommentsAndItsLikesByPublicationUuid(publicationUuid, pageable);
        List<PublicationCommentGetResponseDto> responseDtos = publicationCommentService.dtoFromRawData(page.getContent());
        return PageResponseDto.of(page, responseDtos);
    }

    @Transactional
    public void updateComment(UUID publicationUuid, CommentUpdateDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PublicationComment publicationComment = commentRepository.getCommentByUserUuid(dto.commentUuid())
                .orElseThrow(() -> new CommentNotFoundException("", HttpStatusCode.valueOf(404)));
        if(!(publicationComment.getUser().getUsername().equals(authentication.getName()) ||
                authentication.getAuthorities().contains("ROLE_ADMIN") ||
                publicationComment.getPublication().getId() == publicationUuid))
            throw new AccessDeniedException("User has no access to update the comment" + dto.commentUuid(), HttpStatusCode.valueOf(403));
        commentRepository.updateCommentMessage(dto.text(), dto.commentUuid());
    }

    @Transactional
    public void delete(UUID publicationUuid, UUID commentUuid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PublicationComment publicationComment = commentRepository.getCommentByUserUuid(commentUuid)
                .orElseThrow(() -> new CommentNotFoundException("", HttpStatusCode.valueOf(404)));
        if(!(publicationComment.getUser().getUsername().equals(authentication.getName()) ||
                authentication.getAuthorities().contains("ROLE_ADMIN") ||
                publicationComment.getPublication().getId() == publicationUuid))
            throw new AccessDeniedException("User has no access to update the comment" + commentUuid, HttpStatusCode.valueOf(403));
        commentRepository.deleteByCommentUuid(commentUuid);
    }

    @Transactional(readOnly = true)
    public List<PublicationCommentGetResponseDto> dtoFromRawData(List<PublicationComment> publicationComments) {
        return publicationComments.stream()
                .map(publicationComment -> {
                    CommentLikeAndDislikeDto likesAndDislikesDto = publicationCommentLikeService.getCommentLikesAndDislikes(publicationComment.getId());
                    boolean userLiked = publicationCommentLikeService.isUserLiked(publicationComment.getUser().getId(), publicationComment.getId());
                    return commentMapper.getResponseDtoFromPublicationComment(publicationComment, likesAndDislikesDto, userLiked);
                }).toList();
    }
}
