package io.seoLeir.blog.mapper;

import io.seoLeir.blog.entity.User;
import io.seoLeir.blog.dto.user.UserProfileResponseDto;
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
