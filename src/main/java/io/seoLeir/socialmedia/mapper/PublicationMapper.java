package io.seoLeir.socialmedia.mapper;

import io.seoLeir.socialmedia.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.service.UserService;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring",
        uses = {UserService.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PublicationMapper {

    @Mapping(target = "id", source = "publication.id")
    @Mapping(target = "tittle", source = "publication.tittle")
    @Mapping(target = "user", source = "username")
    @Mapping(target = "publicationFiles", source = "detachedFiles")
    @Mapping(target = "likes", source = "dto.likes")
    @Mapping(target = "dislikes", source = "dto.dislikes")
    @Mapping(target = "timeToRead", source = "publication.timeToReadInMinutes")
    @Mapping(target = "isBookmarkedByCurrentUser", source = "isBookmarkedByUser")
    @Mapping(target = "isLikedByCurrentUser", source = "isLikedByUser")
    @Mapping(target = "bookmarksCount", source = "bookmarksCount")
    public abstract PublicationGetResponseDto getResponseDtoFromPublication(
            Publication publication,
            String username,
            List<UUID> detachedFiles,
            PublicationLikeAndDislikeDto dto,
            Long bookmarksCount,
            Boolean isLikedByUser,
            Boolean isBookmarkedByUser);
}
