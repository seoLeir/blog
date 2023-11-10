package io.seoLeir.socialmedia.mapper;

import io.seoLeir.socialmedia.dto.user.UserProfileResponseDto;
import io.seoLeir.socialmedia.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {

    @Mappings({
           @Mapping(target = "username", source = "user.username"),
           @Mapping(target = "email", source = "user.email"),
           @Mapping(target = "info", source = "user.info"),
           @Mapping(target = "createdAt", source = "user.createdAt")
    })
    public abstract UserProfileResponseDto getResponseDtoFromPublicationComment(User user,
                                                                                Long publicationCount,
                                                                                Long commentCount,
                                                                                Long bookmarksCount,
                                                                                Long followers,
                                                                                Long following,
                                                                                Boolean isUserFollowed);
}
