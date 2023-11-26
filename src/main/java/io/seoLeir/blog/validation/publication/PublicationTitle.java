package io.seoLeir.blog.validation.publication;

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
@Size(max = 50, message = "Comment title cannot exceed 50 characters")
@NotBlank(message = "Publication title should not be blank")
public @interface PublicationTitle {
    String message() default "Invalid publication title";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
