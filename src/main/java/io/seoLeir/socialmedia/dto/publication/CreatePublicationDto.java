package io.seoLeir.socialmedia.dto.publication;


import lombok.Value;
import org.springframework.util.MimeType;

@Value
public class CreatePublicationDto {
    String text;
    String header;
    MimeType file;
}
