package io.seoLeir.socialmedia.validation.publication;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Constraint(validatedBy = { })
@Size(min = 300, message = "Publication text must be at least 300 characters long")
@NotBlank(message = "Publication text should not be blank")
public @interface PublicationText {
    String message() default "Invalid publication text";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
