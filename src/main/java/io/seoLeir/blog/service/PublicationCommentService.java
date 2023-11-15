package io.seoLeir.blog.service;

import io.seoLeir.blog.dto.comment.CommentCreateRequestDto;
import io.seoLeir.blog.dto.comment.CommentCreateResponseDto;
import io.seoLeir.blog.dto.comment.CommentUpdateDto;
import io.seoLeir.blog.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.blog.dto.publication.ActionType;
import io.seoLeir.blog.entity.User;
import io.seoLeir.blog.exception.user.UserNotFountException;
import io.seoLeir.blog.repository.PublicationCommentRepository;
import io.seoLeir.socialmedia.dto.comment.*;
import io.seoLeir.blog.dto.page.PageRequestDto;
import io.seoLeir.blog.dto.page.PageResponseDto;
import io.seoLeir.blog.dto.publication.PublicationActionWithStatusRequest;
import io.seoLeir.blog.entity.Publication;
import io.seoLeir.blog.entity.PublicationComment;
import io.seoLeir.blog.exception.comment.CommentNotFoundException;
import io.seoLeir.blog.exception.publication.AccessDeniedException;
import io.seoLeir.blog.exception.publication.InvalidActionType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationCommentService {
    private final PublicationCommentRepository commentRepository;
    private final PublicationCommentLikeService publicationCommentLikeService;
    private final PublicationService publicationService;
    private final UserService userService;


    @Transactional(readOnly = true)
    public long getPublicationCommentsCountByUserUuid(UUID userUuid){
        return commentRepository.getAllByUser(userUuid);
    }

    @Transactional(readOnly = true)
    public PageResponseDto<PublicationCommentGetResponseDto> getUserComments(String username, PageRequestDto dto, String currentUsername) {
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        User current = userService.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        Pageable pageable = PageRequestDto.toPageable(dto);
        Page<PublicationCommentGetResponseDto> responseDtos= commentRepository.getUserComments(user.getId(),current.getId(), pageable);
        return PageResponseDto.of(responseDtos);
    }

    @Transactional
    public CommentCreateResponseDto createComment(UUID publicationUuid,
                                                  CommentCreateRequestDto dto,
                                                  UUID parentCommentUuid,
                                                  String username){
        Publication publication = publicationService.getPublication(publicationUuid);
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        PublicationComment publicationComment;
        if (parentCommentUuid == null){
            publicationComment = new PublicationComment(UUID.randomUUID(), user, publication, dto.commentText());
        }else {
            PublicationComment parentComment = commentRepository.findPublicationCommentByUuid(parentCommentUuid)
                    .orElseThrow(() -> new CommentNotFoundException("Parent comment not found: " + parentCommentUuid, HttpStatusCode.valueOf(404)));
            publicationComment = new PublicationComment(UUID.randomUUID(), user, publication, parentComment, dto.commentText());
        }
        commentRepository.save(publicationComment);
        return new CommentCreateResponseDto(publicationComment.getId());
    }

    @Transactional
    public ResponseEntity<?> likeOrDislikeOrUpdateComment(UUID commentUuid, String username, PublicationActionWithStatusRequest dto){
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        PublicationComment publicationComment = commentRepository.getReferenceById(commentUuid);
        ActionType actionType = ActionType.valueOf(dto.action().toUpperCase());
        switch (actionType){
            case CREATE ->{
                publicationCommentLikeService.create(user, publicationComment, dto.status());
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(201))
                        .build();
            }
            case UPDATE ->{
                publicationCommentLikeService.update(user, publicationComment, dto.status());
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(202))
                        .build();
            }
            case DELETE ->{
                publicationCommentLikeService.remove(user, publicationComment);
                return ResponseEntity
                        .status(HttpStatusCode.valueOf(204))
                        .build();
            }
            default ->
                throw new InvalidActionType("Invalid action type: " + actionType, HttpStatusCode.valueOf(400));
        }
    }


    @Transactional(readOnly = true)
    public PageResponseDto<PublicationCommentGetResponseDto> getPublicationComments(UUID publicationUuid, PageRequestDto dto, String currentUser){
        Pageable pageable = PageRequestDto.toPageable(dto);
        User user = userService.findByUsername(currentUser)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        Page<PublicationCommentGetResponseDto> responseDtos = commentRepository.getPublicationComments(publicationUuid, user.getId(), pageable);
        return PageResponseDto.of(responseDtos);
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
    public void deleteComment(UUID publicationUuid, UUID commentUuid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PublicationComment publicationComment = commentRepository.getCommentByUserUuid(commentUuid)
                .orElseThrow(() -> new CommentNotFoundException("", HttpStatusCode.valueOf(404)));
        if(!(publicationComment.getUser().getUsername().equals(authentication.getName()) ||
                authentication.getAuthorities().contains("ROLE_ADMIN") ||
                publicationComment.getPublication().getId() == publicationUuid))
            throw new AccessDeniedException("User has no access to update the comment" + commentUuid, HttpStatusCode.valueOf(403));
        commentRepository.deleteByCommentUuid(commentUuid);
    }
}
