package io.seoLeir.blog.exception.file;


import io.seoLeir.blog.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UnsupportedFileExtension extends SocialMediaException {
    public UnsupportedFileExtension(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
