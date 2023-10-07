package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.entity.PublicationFile;
import io.seoLeir.socialmedia.repository.PublicationFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class PublicationFileService {
    private final PublicationFileRepository publicationFileRepository;


    @Transactional
    public void save(PublicationFile publicationFile){
        publicationFileRepository.save(publicationFile);
    }

    @Transactional
    public List<UUID> findByFileByPublicationId(UUID uuid){
        return publicationFileRepository.findAllByPublicationId(uuid);
    }
}
