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
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
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
        if(multipartFile.isEmpty())
            throw new EmptyFileException("The file cannot be empty", HttpStatusCode.valueOf(400));
        String fileExtension = ExtensionResolver
                .getFileExtension(Objects.requireNonNull(multipartFile.getOriginalFilename()))
                .orElseThrow(() -> new UnsupportedFileExtension("This file extension is not supported",
                        HttpStatusCode.valueOf(415)));
        Set<String> validExtensionForMimetype = properties.getValidExtensionForMimetype(multipartFile.getContentType())
                .orElseThrow(() -> new InvalidMimeType("Invalid mime type", HttpStatusCode.valueOf(415)));
        if (!validExtensionForMimetype.contains(fileExtension))
            throw new UnsupportedFileExtension("Unsupported multipartFile extension",
                    HttpStatusCode.valueOf(415));
        File fileToSave = new File(
                UUID.randomUUID(),
                multipartFile.getOriginalFilename(),
                multipartFile.getContentType(),
                user);
        fileRepository.save(fileToSave);
        if (!Files.exists(properties.getBasicDirectory()))
            Files.createDirectory(properties.getBasicDirectory());
        Files.copy(multipartFile.getInputStream(),
                properties.getBasicDirectory().resolve(fileToSave.getFilename().toString()),
                StandardCopyOption.REPLACE_EXISTING);
        return fileToSave.getFilename();
    }

    @Transactional
    public FoundFile getFile(UUID fileUUID){
        try {
            Resource resource = new UrlResource(properties.getBasicDirectory().resolve(fileUUID.toString()).toUri());
            FoundFile foundFile = fileRepository.findById(fileUUID)
                    .map(file -> new FoundFile(file.getRealName(), file.getMimeType(), resource))
                    .orElseThrow(() -> new FileNotFoundException("File with uuid:" + fileUUID + " not found", HttpStatusCode.valueOf(404)));
            if (!resource.exists()){
                log.error("File with UUID: {} exists in database but not in file system", fileUUID);
                throw new FileNotFoundException("File with uuid:" + fileUUID + " not found", HttpStatusCode.valueOf(500));
            }
            return foundFile;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public boolean isExistById(UUID id){
        return fileRepository.existsByFilename(id);
    }

    @Transactional
    public File findFileById(UUID id){
        return fileRepository.findById(id).orElseThrow(() -> new FileNotFoundException(
                "File not found. File UUID: " + id, HttpStatusCode.valueOf(404)));
    }

    @Transactional
    public boolean isFileRelatedToUser(UUID fileUuid, String username) {
        File file = fileRepository.findById(fileUuid).orElseThrow(() -> new FileNotFoundException(
                "File not found. File UUID: " + fileUuid, HttpStatusCode.valueOf(404)));
        return username.equals(file.getUser().getUsername());
    }
}
