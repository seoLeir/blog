package io.seoLeir.socialmedia.controller;

import io.seoLeir.socialmedia.dto.file.FileCreatedResponse;
import io.seoLeir.socialmedia.dto.file.FoundFile;
import io.seoLeir.socialmedia.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;


@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileRestController {

    private final FileService fileService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FileCreatedResponse uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return new FileCreatedResponse(fileService.upload(file, "seoLeir"));
    }

    @SneakyThrows
    @GetMapping("/{file-uuid}")
    public ResponseEntity<Resource> getFile(@PathVariable("file-uuid") UUID fileName){
        FoundFile file = fileService.getFile(fileName);
        ContentDisposition contentDisposition = ContentDisposition
                .inline()
                .filename(file.filename())
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDisposition(contentDisposition);
        httpHeaders.setContentType(MediaType.asMediaType(MimeType.valueOf(file.contentType())));
        httpHeaders.setContentLength(file.resource().contentLength());
        return ResponseEntity.ok().headers(httpHeaders).body(file.resource());
    }
}
