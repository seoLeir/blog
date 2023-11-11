package io.seoLeir.socialmedia.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.*;
import org.mapstruct.Mapping;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Constraint(validatedBy = { })
@Size(min = 5, max = 32, message = "Username must be between 5 and 32 characters")
@NotBlank(message = "Username should not be blank")
@Pattern(regexp = "^(?:[a-zA-Z]|_[a-zA-Z])+(?:_?[0-9]+[a-zA-Z]*)*$", message = "Invalid username")
public @interface Username {
    String message() default "Invalid username";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
