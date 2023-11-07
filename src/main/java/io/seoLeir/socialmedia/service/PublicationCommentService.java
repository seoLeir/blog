package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.comment.*;
import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.PublicationComment;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.comment.CommentNotFoundException;
import io.seoLeir.socialmedia.exception.publication.AccessDeniedException;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.repository.PublicationCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
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
    private final PublicationService publicationService;
    private final UserService userService;


    @Transactional
    public long publicationCommentsByUserUuid(UUID userUuid){
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
