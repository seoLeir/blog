package io.seoLeir.socialmedia.util;

import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class ExtensionResolver {


    public static Optional<String> getFileExtension(String fileName){
        int i = fileName.indexOf(".");
        String extension = fileName.substring(i);
        return Optional.of(extension);
    }
}
