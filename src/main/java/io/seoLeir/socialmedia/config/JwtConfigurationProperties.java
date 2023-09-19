package io.seoLeir.socialmedia.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Value
@ConfigurationProperties(prefix = "jwt")
public class JwtConfigurationProperties {
    Duration lifetime;
}
