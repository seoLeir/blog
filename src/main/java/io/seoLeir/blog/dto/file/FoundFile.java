package io.seoLeir.blog.dto.file;

import org.springframework.core.io.Resource;

public record FoundFile(String filename, String contentType, Resource resource) {
}
