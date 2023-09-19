package io.seoLeir.socialmedia.config;


import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.*;

@Value
@ConfigurationProperties(prefix = "socialmedia")
public class SocialMediaProperties {
    Files files;
    @Value
    public static class Files{
        String path;
        Map<String, Set<String>> mimeTypeToValidExtension = new HashMap<>();
    }


    public Optional<Set<String>> getValidExtensionForMimetype(String mimetype){
        return Optional.ofNullable(files.getMimeTypeToValidExtension().get(mimetype));
    }
}
