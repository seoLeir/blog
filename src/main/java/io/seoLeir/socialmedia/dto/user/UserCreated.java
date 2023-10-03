package io.seoLeir.socialmedia.dto.user;

import lombok.Value;
import org.springframework.http.HttpStatusCode;

@Value
public class UserCreated {
    HttpStatusCode statusCode;
    String description;
}
