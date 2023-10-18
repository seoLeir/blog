package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationUpdateRequestDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.PublicationFile;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.file.FileNotFoundException;
import io.seoLeir.socialmedia.exception.publication.PublicationNotFound;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.repository.PublicationFileRepository;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import io.seoLeir.socialmedia.specifications.PublicationSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationFileRepository publicationFileRepository;
    private final PublicationRepository publicationRepository;
    private final PublicationFileService publicationFileService;
    private final FileService fileService;
    private final UserService userService;

    @Value("${socialmedia.reading-speed}")
    private Integer averagePersonReadingSpeed;

    @Transactional
    public UUID createPublication(PublicationCreateRequestDto publicationDto, String publisherName){
        User user = userService.findByUsername(publisherName)
                .orElseThrow(() -> new UserNotFountException("User with username " + publisherName + " not found", HttpStatusCode.valueOf(404)));
        int wordsCountInPublicationText = wordsCountInString(publicationDto.text());
        int minutesToRead = wordsCountInPublicationText / averagePersonReadingSpeed;
        Publication publication = new Publication(
                UUID.randomUUID(), publicationDto.header(), publicationDto.text(), user, minutesToRead);
        publicationRepository.save(publication);
        publicationDto.files().forEach(uuid -> {
            if (fileService.isExistById(uuid)) {
                PublicationFile publicationFile = new PublicationFile(publication, fileService.findFileById(uuid));
                publicationFileRepository.save(publicationFile);
            }
        });
        return publication.getId();
    }

    @Transactional
    public Optional<PublicationGetResponseDto> getPublication(UUID publicationUuid){
        List<UUID> fileList = publicationFileService.findByFileByPublicationId(publicationUuid).stream()
                .map(uuid -> {
                    if (fileService.isExistById(uuid)) {
                        return uuid;
                    } else {
                        log.error("File with this uuid: {} not found", uuid);
                        throw new FileNotFoundException("File not found", HttpStatusCode.valueOf(404));
                    }
                }).toList();
        Publication publication = publicationRepository.getById(publicationUuid);
        Long newViewCount = publication.getViewCount() + 1;
        publicationRepository.updateViewCount(newViewCount);
        return Optional.of(PublicationGetResponseDto.of(publication, fileList));
    }

    @Transactional
    public PageResponseDto<Publication> getAllUserPublications(
            String publisherName, PageRequestDto dto, String textToSearch){
        Pageable pageable = PageRequest.of(dto.pageNumber(), dto.pageSize(), dto.sort());
        Page<Publication> publicationPage = publicationRepository
                .findAll(PublicationSpecification.publicationOrderBySpecification(textToSearch, publisherName),
                        pageable);
        return PageResponseDto.of(publicationPage);
    }

    @Transactional
    public void delete(UUID id, String username){
        Publication publication = publicationRepository.findById(id).orElseThrow(() ->
                new PublicationNotFound("Publication" + id + " not found", HttpStatusCode.valueOf(404)));
        if (publication.getUser().getUsername().equals(username) || username.equals("admin")){
            publicationRepository.deleteById(id);
        }
    }

    @Transactional
    public Long getAllPublicationsCountByUsername(String username){
        return publicationRepository.getPublicationCountByUserUsername(username);
    }

    @Transactional
    public void update(PublicationUpdateRequestDto dto, UUID id) {
        if (publicationRepository.existsById(id))
            publicationRepository.updateTittleAndText(dto.tittle(), dto.publicationText(), id);
        else
            throw new PublicationNotFound("Publication with id:" + id + "not found",
                    HttpStatusCode.valueOf(404));
    }

    @Transactional
    public PageResponseDto<Publication> getAllUserBookmarkedPublication(List<UUID> publicationsUuid, Pageable pageable){
        return PageResponseDto.of(publicationRepository.getAllUserBookmarkedPublication(publicationsUuid, pageable));
    }

    private int wordsCountInString(String publicationText){
        final int WORD = 0;
        final int SEPARATOR = 1;
        if (publicationText == null) {
            return 0;
        }
        int flag = SEPARATOR;
        int count = 0;
        int stringLength = publicationText.length();
        int characterCounter = 0;

        while (characterCounter < stringLength) {
            if (isAllowedInWord(publicationText.charAt(characterCounter)) && flag == SEPARATOR) {
                flag = WORD;
                count++;
            } else if (!isAllowedInWord(publicationText.charAt(characterCounter))) {
                flag = SEPARATOR;
            }
            characterCounter++;
        }
        return count;
    }

    private boolean isAllowedInWord(char charAt) {
        return charAt == '\'' || Character.isLetter(charAt);
    }
}
