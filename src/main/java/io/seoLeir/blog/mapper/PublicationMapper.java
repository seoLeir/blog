package io.seoLeir.blog.mapper;

import io.seoLeir.blog.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.blog.dto.publication.PublicationGetResponseDto;
import io.seoLeir.blog.entity.Publication;
import org.mapstruct.*;

import java.util.List;
import java.util.UUID;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PublicationMapper {

    @Mappings({
            @Mapping(target = "id", source = "publication.id"),
            @Mapping(target = "title", source = "publication.title"),
            @Mapping(target = "user", source = "username"),
            @Mapping(target = "publicationFiles", source = "detachedFiles"),
            @Mapping(target = "likes", source = "dto.likes"),
            @Mapping(target = "dislikes", source = "dto.dislikes"),
            @Mapping(target = "timeToRead", source = "publication.timeToReadInMinutes"),
            @Mapping(target = "isBookmarkedByCurrentUser", source = "isBookmarkedByUser"),
            @Mapping(target = "isLikedByCurrentUser", source = "isLikedByUser"),
            @Mapping(target = "bookmarksCount", source = "bookmarksCount")
    })
    public abstract PublicationGetResponseDto getResponseDtoFromPublication(
            Publication publication,
            String username,
            List<UUID> detachedFiles,
            PublicationLikeAndDislikeDto dto,
            Long bookmarksCount,
            Boolean isLikedByUser,
            Boolean isBookmarkedByUser);
}
