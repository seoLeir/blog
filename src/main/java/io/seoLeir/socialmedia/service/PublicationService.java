package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.page.PageRequestDto;
import io.seoLeir.socialmedia.dto.page.PageResponseDto;
import io.seoLeir.socialmedia.dto.publication.PublicationCreateRequestDto;
import io.seoLeir.socialmedia.dto.publication.PublicationGetResponseDto;
import io.seoLeir.socialmedia.entity.Publication;
import io.seoLeir.socialmedia.entity.PublicationFile;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.entity.keys.PublicationFileId;
import io.seoLeir.socialmedia.exception.file.FileNotFoundException;
import io.seoLeir.socialmedia.exception.user.UserNotFountException;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final PublicationRepository publicationRepository;
    private final PublicationFileService publicationFileService;
    private final FileService fileService;
    private final UserService userService;

    @Transactional
    public UUID createPublication(PublicationCreateRequestDto publicationDto, String publisherName){
        User user = userService.findByUsername(publisherName)
                .orElseThrow(() -> new UserNotFountException("User with username " + publisherName + " not found", HttpStatusCode.valueOf(404)));
        Publication publication = new Publication(UUID.randomUUID(), publicationDto.header(), publicationDto.text(), user);
        publicationRepository.save(publication);
        fileService.getAllFilesByUserUuid(user.getId()).stream()
                .map(file -> {
                    if (fileService.isExistById(file.getFilename())) {
                        return new PublicationFile(publication, file);
                    } else {
                        throw new FileNotFoundException("File not found. File UUID: " + file.getFilename(), HttpStatusCode.valueOf(404));
                    }
                }).forEach(publicationFileService::save);
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
        Publication publication = publicationRepository.getReferenceById(publicationUuid);
        return Optional.of(new PublicationGetResponseDto(
                publication.getId(),
                publication.getTittle(),
                publication.getText(),
                publication.getUser().getUsername(),
                publication.getCreatedDate(), fileList));
    }

    @Transactional
    public PageResponseDto<Publication> getAllUserPublications(String publisherName, PageRequestDto dto){
//        Pageable pageable = PageRequest.of(dto.pageNumber(), dto.pageSize(), dto.sort());
//        Sort sort = Sort.by(Publication.Fields.createdDate);
//        publicationRepository.getAllByUserId()
//        return PageResponseDto.of()
        return null;
    }
}
