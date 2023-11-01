package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationLikeAndDislikeDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationUpdateRequestDto;
import io.seoLeir.socialmedia.entity.*;
import io.seoLeir.socialmedia.exception.publication.AccessDeniedException;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFoundException;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.mapper.PublicationMapper;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import io.seoLeir.socialmedia.specifications.PublicationSpecification;
import io.seoLeir.socialmedia.util.JwtTokenUtils;
import io.seoLeir.socialmedia.util.WordCounterUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static io.seoLeir.socialmedia.dto.page.PageRequestDto.toPageable;
import static org.antlr.v4.runtime.tree.xpath.XPath.findAll;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final PublicationFileService publicationFileService;
    private final PublicationLikeService publicationLikeService;
    private final UserBookmarkService userBookmarkService;
    private final FileService fileService;
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final PublicationMapper publicationMapper;
    private final WordCounterUtils wordCounterUtils;

    @Lazy
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private PublicationService publicationService;

    @Value("${socialmedia.reading-speed}")
    private Integer averagePersonReadingSpeed;

    @Transactional
    public UUID createPublication(PublicationCreateRequestDto publicationDto, String publisherName){
        User user = userService.findByUsername(publisherName)
                .orElseThrow(() -> new UserNotFountException("User with username " + publisherName + " not found", HttpStatusCode.valueOf(404)));
        int wordsCountInPublicationText = wordCounterUtils.wordsCountInString(publicationDto.text());
        int minutesToRead = wordsCountInPublicationText / averagePersonReadingSpeed;
        Publication publication = new Publication(
                UUID.randomUUID(), publicationDto.header(), publicationDto.text(), user, minutesToRead);
        publicationRepository.save(publication);
        publicationDto.files().forEach(uuid -> {
            if (fileService.isExistById(uuid)) {
                PublicationFile publicationFile = new PublicationFile(publication, fileService.findFileById(uuid));
                publicationFileService.save(publicationFile);
            }
        });
        return publication.getId();
    }

    @Transactional
    public Optional<PublicationGetResponseDto> getPublicationResponseDto(UUID publicationUuid){
        Publication publication = publicationRepository.getPublicationById(publicationUuid)
                .orElseThrow(() -> new PublicationNotFoundException(
                        "Publication with uuid:" + publicationUuid + "not found",
                        HttpStatusCode.valueOf(404)));
        Long newViewCount = publication.getViewCount() + 1;
        publicationRepository.updateViewCount(newViewCount);
        List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(List.of(publication));
        return Optional.of(responseDtoList.get(0));
    }

    @Transactional
    public Publication getPublication(UUID publicationUuid){
        return publicationRepository.getPublicationById(publicationUuid)
                .orElseThrow(() -> new PublicationNotFoundException(
                        "Publication with uuid:" + publicationUuid + "not found",
                        HttpStatusCode.valueOf(404)));
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getAllUserPublications(
            String publisherName, PageRequestDto dto, String textToSearch){
        UUID uuid = userService.getUserUuidFromUsername(publisherName)
                .orElseThrow(() -> new UserNotFountException("User not found", HttpStatusCode.valueOf(404)));
        PublicationSpecification publicationSpecification;
        if (textToSearch == null){
            publicationSpecification = new PublicationSpecification(Map.of( "id", uuid));
        }else {
            publicationSpecification = new PublicationSpecification(Map.of( "id", uuid, "title", textToSearch));
        }
        Page<Publication> publicationPage = publicationRepository.findAll(
                publicationSpecification.getPublicationSpecification(), PageRequestDto.toPageable(dto));
        List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(publicationPage.getContent());
        return PageResponseDto.of(publicationPage, responseDtoList);
    }

    @Transactional
    public void delete(UUID id, String username){
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Publication publication = publicationRepository.findById(id).orElseThrow(() ->
                new PublicationNotFoundException("Publication" + id + " not found", HttpStatusCode.valueOf(404)));
        if (publication.getUser().getUsername().equals(username) || authorities.contains("ROLE_ADMIN")){
            publicationRepository.deleteById(id);
        }
    }

    @Transactional
    public void update(PublicationUpdateRequestDto dto, UUID id, String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!publicationRepository.existsById(id))
            throw new PublicationNotFoundException("Publication with id:" + id + "not found", HttpStatusCode.valueOf(404));
        if(authentication.getName().equals(publicationRepository.getPublicationPublisherNameByPublicationUuid(id))
                || jwtTokenUtils.getRoles(token).contains("ROLE_ADMIN"))
            publicationRepository.updateTittleAndText(dto.tittle(), dto.publicationText(), id);
        else
            throw new AccessDeniedException("Access denied to update the publication: " + id, HttpStatusCode.valueOf(403));
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getFeedWithSpecification(PageRequestDto pageRequestDto,
                                                                            Specification<Publication> specification){
        Page<Publication> page;
        if(specification == null)
            page = publicationRepository.findAll(toPageable(pageRequestDto));
        else
            page = publicationRepository.findAll(specification, toPageable(pageRequestDto));
        List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(page.getContent());
        return PageResponseDto.of(page, responseDtoList);
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getAllUserBookmarkedPublication(List<UUID> publicationsUuid,
                                                                                      PageRequestDto pageDto){
        Page<Publication> allUserBookmarkedPublication = publicationRepository.getAllUserBookmarkedPublication(publicationsUuid, toPageable(pageDto));
        List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(allUserBookmarkedPublication.getContent());
        return PageResponseDto.of(allUserBookmarkedPublication, responseDtoList);
    }

    @Transactional
    public long getAllPublicationsCountByUsername(UUID userUuid) {
        return publicationRepository.getPublicationCountByUserUuid(userUuid);
    }

    @Transactional
    public List<PublicationGetResponseDto> responseContentFromRawData(List<Publication> publicationList) {
        return publicationList.stream()
                .map(publication -> {
                    List<UUID> detachedFiles = publicationFileService.findByFileByPublicationId(publication.getId());
                    PublicationLikeAndDislikeDto publicationLikeAndDislikeDto = publicationLikeService.getPublicationLikesAndDislikesByPublicationUuid(publication.getId());
                    Long publicationBookmarkedCount = userBookmarkService.getPublicationBookmarkedCount(publication.getId());
                    boolean isUserLikedPost = publicationLikeService.isUserLikedThePost(publication.getUser().getId(), publication.getId());
                    boolean isUserBookmarked = userBookmarkService.isUserBookmarkedPublication(publication.getUser().getId(), publication.getId());
                    return publicationMapper.getResponseDtoFromPublication(
                            publication,
                            publication.getUser().getUsername(),
                            detachedFiles,
                            publicationLikeAndDislikeDto,
                            publicationBookmarkedCount,
                            isUserLikedPost,
                            isUserBookmarked
                    );
                }).toList();
    }
}
