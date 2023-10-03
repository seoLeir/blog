package io.seoLeir.socialmedia.mapper;

import io.seoLeir.socialmedia.dto.publication.CreatePublicationDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class PublicationMapper {

    @Mappings(value = {
            @Mapping(target = "user", source = "user")
    })
    public abstract Publication publicationFromCreateDto(CreatePublicationDto dto, User user);

}
