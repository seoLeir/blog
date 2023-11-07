package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.*;
import io.seoLeir.socialmedia.entity.*;
import io.seoLeir.socialmedia.exception.publication.AccessDeniedException;
import io.seoLeir.socialmedia.exception.publication.InvalidPeriodType;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFoundException;
import io.seoLeir.socialmedia.exception.publication.UnsupportedOrderType;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import static io.seoLeir.socialmedia.dto.page.PageRequestDto.toPageable;
import static java.time.temporal.ChronoUnit.*;

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
    @SuppressWarnings(value = "SpringJavaAutowiringInspection")
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
    public Optional<PublicationGetResponseDto> getConcretePublication(UUID publicationUuid){
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

    /*
    * Accepted
    * */
    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getUserFeedByTop(PeriodType period, PageRequestDto pageRequestDto){
        Pageable pageable = PageRequestDto.toPageable(pageRequestDto);
        Instant start;
        switch (period){
            case daily ->
                start = Instant.now().minus(1, DAYS);
            case weekly ->
                start = Instant.now().minus(7, DAYS);
            case monthly ->
                start = LocalDateTime.now().minus(1, MONTHS).toInstant(ZoneOffset.UTC);
            case yearly ->
                start = LocalDateTime.now().minus(1, YEARS).toInstant(ZoneOffset.UTC);
            case alltime ->{
                Page<FeedDto> topPublicationsAllTime = publicationRepository.getTopPublicationsAllTime(pageable);
                List<Publication> raw = topPublicationsAllTime.getContent().stream()
                        .map(feedDto -> {
                            UUID publicationUuid = feedDto.getPublicationUuid();
                            return publicationService.getPublication(publicationUuid);
                        })
                        .toList();
                List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(raw);
                return PageResponseDto.of(topPublicationsAllTime, responseDtoList);
            }
            default ->
                throw new InvalidPeriodType("Illegal argument", HttpStatusCode.valueOf(400));
        }
        Instant end = Instant.now();
        Page<FeedDto> topPublicationsByPeriod = publicationRepository.getTopPublicationsByPeriod(start, end, pageable);
        List<Publication> raw = topPublicationsByPeriod.getContent().stream()
                .map(feedDto -> publicationService.getPublication(feedDto.getPublicationUuid()))
                .toList();
        List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(raw);
        return PageResponseDto.of(topPublicationsByPeriod, responseDtoList);
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getUserFeedByNew(Integer rangeFilter, PageRequestDto page){
        Pageable pageable = toPageable(page);
        if (rangeFilter == null){
            Page<FeedDto> defaultFeeder = publicationRepository.getNewPublicationsDefaultFeeder(pageable);
            List<Publication> raw = defaultFeeder.getContent().stream()
                    .map(feedDto -> publicationService.getPublication(feedDto.getPublicationUuid()))
                    .toList();
            List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(raw);
            return PageResponseDto.of(defaultFeeder, responseDtoList);
        }else {
            Page<FeedDto> defaultFeeder = publicationRepository.getNewPublicationsByRangeFilter(rangeFilter, pageable);
            List<Publication> raw = defaultFeeder.getContent().stream()
                    .map(feedDto -> publicationService.getPublication(feedDto.getPublicationUuid()))
                    .toList();
            List<PublicationGetResponseDto> responseDtoList = publicationService.responseContentFromRawData(raw);
            return PageResponseDto.of(defaultFeeder, responseDtoList);
        }
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getPublicationsWithSearchFilter(String textToSearch,
                                                                                      OrderType orderType,
                                                                                      PageRequestDto pageRequestDto){
        Pageable pageable = PageRequestDto.toPageable(pageRequestDto);
        switch (orderType){
            case popularity -> {

                Page<FeedDto> page = publicationRepository.searchPublicationByPopularityOrder(textToSearch, pageable);
                List<Publication> raw = page.getContent().stream()
                        .map(feedDto -> publicationService.getPublication(feedDto.getPublicationUuid()))
                        .toList();
                List<PublicationGetResponseDto> response = publicationService.responseContentFromRawData(raw);
                return PageResponseDto.of(page, response);
            }
            case date -> {
                Page<Publication> page = publicationRepository.searchPublicationByCreatedDateOrder(textToSearch, pageable);
                List<PublicationGetResponseDto> response = publicationService.responseContentFromRawData(page.getContent());
                return PageResponseDto.of(page, response);
            }
            default ->
                throw new UnsupportedOrderType("This order type not supported", HttpStatusCode.valueOf(400));
        }
    }

    @Transactional
    public PageResponseDto<PublicationGetResponseDto> getAllUserBookmarkedPublication(List<UUID> publicationsUuid,
                                                                                      PageRequestDto pageDto) {
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
