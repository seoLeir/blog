package io.seoLeir.socialmedia.service;

import io.seoLeir.socialmedia.config.SocialMediaFileConfigurationProperties;
import io.seoLeir.socialmedia.dto.file.FoundFile;
import io.seoLeir.socialmedia.entity.File;
import io.seoLeir.socialmedia.entity.User;
import io.seoLeir.socialmedia.exception.file.*;
import io.seoLeir.socialmedia.repository.FileRepository;
import io.seoLeir.socialmedia.util.ExtensionResolver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {
    private final UserService userService;
    private final FileRepository fileRepository;
    private final SocialMediaFileConfigurationProperties properties;

    @Transactional
    public UUID upload(MultipartFile multipartFile, String publisherName) throws IOException {
        User user = userService.findByUsername(publisherName).orElseThrow();
        if(multipartFile.isEmpty()){
            throw new EmptyFileException("multipartFile should not be empty");
        }
        String fileExtension = ExtensionResolver.getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()))
                .orElseThrow(() -> new UnsupportedFileExtension("Unsupported file extension"));
        Set<String> validExtensionForMimetype = properties.getValidExtensionForMimetype(multipartFile.getContentType())
                .orElseThrow(() -> new InvalidMimeType("Invalid mime type"));
        if (!validExtensionForMimetype.contains(fileExtension)){
            throw new InvalidFileExtensionException("Unsupported multipartFile extension");
        }
        File fileToSave = new File(UUID.randomUUID(), multipartFile.getOriginalFilename(), fileExtension, multipartFile.getContentType(), user);
        fileRepository.save(fileToSave);
        Files.copy(multipartFile.getInputStream(), properties.getBasicDirectory().resolve(fileToSave.getFilename().toString()), StandardCopyOption.REPLACE_EXISTING);
        return fileToSave.getFilename();
    }

    @Transactional
    public FoundFile getFile(UUID fileUUID){
        try {
            Resource resource = new UrlResource(properties.getBasicDirectory().resolve(fileUUID.toString()).toUri());
            FoundFile foundFile = fileRepository.findById(fileUUID)
                    .map(file -> new FoundFile(file.getRealName(), file.getMimeType(), resource))
                    .orElseThrow(() -> new FileNotFoundException("File with uuid:" + fileUUID + " not found"));
            if (!resource.exists()){
                log.error("File with UUID: {} exists in database but not in file system", fileUUID);
                throw new FileNotFoundException("File with uuid:" + fileUUID + " not found");
            }
            return foundFile;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
