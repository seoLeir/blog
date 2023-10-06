package io.seoLeir.socialmedia.exception.file;


import io.seoLeir.socialmedia.exception.SocialMediaException;
import org.springframework.http.HttpStatusCode;

public class UnsupportedFileExtension extends SocialMediaException {
    public UnsupportedFileExtension(String message, HttpStatusCode httpStatusCode) {
        super(message, httpStatusCode);
    }
}
