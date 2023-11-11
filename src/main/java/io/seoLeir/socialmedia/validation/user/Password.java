package io.seoLeir.socialmedia.validation.user;

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
@Size(min = 10, max = 218, message = "Password must be between 10 and 128 characters")
@NotBlank(message = "Password should not be blank")
public @interface Password {
    String message() default "Invalid password";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
