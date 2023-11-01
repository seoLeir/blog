package io.seoLeir.socialmedia.mapper;

import io.seoLeir.socialmedia.dto.comment.CommentLikeAndDislikeDto;
import io.seoLeir.socialmedia.dto.comment.PublicationCommentGetResponseDto;
import io.seoLeir.socialmedia.entity.PublicationComment;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PublicationCommentMapper {

    @Mappings({
            @Mapping(target = "publicationCommentUuid", source = "publicationComment.id"),
            @Mapping(target = "userUuid", source = "publicationComment.user.id"),
            @Mapping(target = "publicationUuid", source = "publicationComment.publication.id"),
            @Mapping(target = "parentCommentUuid", source = "publicationComment.parentPublicationComment.id"),
            @Mapping(target = "commentMessage", source = "publicationComment.commentMessage"),
            @Mapping(target = "createdAt", source = "publicationComment.createdAt"),
            @Mapping(target = "likes", source = "likeAndDislikeDto.likes"),
            @Mapping(target = "dislikes", source = "likeAndDislikeDto.dislikes"),
            @Mapping(target = "isLikedByCurrentUser", source = "isLikedByUser")
    })
    public abstract PublicationCommentGetResponseDto getResponseDtoFromPublicationComment(PublicationComment publicationComment,
                                                                                   CommentLikeAndDislikeDto likeAndDislikeDto,
                                                                                   Boolean isLikedByUser);
}
