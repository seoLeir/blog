package io.seoLeir.blog.validation.comment;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Constraint(validatedBy = { })
@Size(min = 1, max = 256, message = "Comment text cannot exceed 256 characters")
@NotEmpty(message = "Comment text should not be blank")
public @interface CommentText {
    String message() default "Invalid comment text";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
