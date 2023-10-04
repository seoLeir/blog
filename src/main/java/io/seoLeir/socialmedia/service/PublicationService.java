package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.dto.publication.CreatePublicationDto;
import io.seoLeir.socialmedia.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private FileService fileService;

    public void createPublication(CreatePublicationDto publicationDto){
        fileService.getFile(publicationDto.filename());
    }
}
