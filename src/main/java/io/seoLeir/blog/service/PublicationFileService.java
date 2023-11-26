package io.seoLeir.blog.service;

import io.seoLeir.blog.repository.PublicationFileRepository;
import io.seoLeir.blog.entity.PublicationFile;
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

    @Transactional(readOnly = true)
    public List<UUID> findByFileByPublicationId(UUID uuid){
        return publicationFileRepository.findAllByPublicationId(uuid);
    }
}
