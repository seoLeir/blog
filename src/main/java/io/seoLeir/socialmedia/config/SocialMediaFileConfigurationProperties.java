package io.seoLeir.socialmedia.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.util.*;

@Data
@ConfigurationProperties(prefix = "socialmedia.files")
public class SocialMediaFileConfigurationProperties {
    Path basicDirectory;
    Map<String, Set<String>> mimeTypeToValidExtension = new HashMap<>();

    public Optional<Set<String>> getValidExtensionForMimetype(String mimetype){
        return Optional.ofNullable(getMimeTypeToValidExtension().get(mimetype));
    }

    public Path getBasicDirectory(){
        return this.basicDirectory;
    }
}
