package io.seoLeir.socialmedia.exception;

import io.seoLeir.socialmedia.dto.SocialMediaError;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ElementKind;
import jakarta.validation.Path;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(SocialMediaException.class)
    public SocialMediaError handleSocialMediaException(SocialMediaException ex, WebRequest webRequest) {
        log.error("Social media exception was caught: ", ex);
        return SocialMediaError.builder()
                .statusCode(ex.getHttpStatusCode())
                .errorDateTime(ex.getTimestamp())
                .errorDescription(ex.getMessage())
                .path(webRequest.getContextPath())
                .build();
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> processConstraintViolationException(ConstraintViolationException processedException) {
        Map<String, List<String>> errorsDescriptions = new HashMap<>();
        List<ConstraintViolation<?>> unprocessedViolations = new ArrayList<>();
        for (var constraintViolation : processedException.getConstraintViolations()) {
            if (constraintViolation.getRootBeanClass().isAnnotationPresent(RestController.class)) {
                for (Path.Node node : constraintViolation.getPropertyPath()) {
                    if (node.getKind() == ElementKind.PARAMETER) {
                        errorsDescriptions.computeIfAbsent(node.getName(), k -> new ArrayList<>()).add(constraintViolation.getMessage());
                    }
                }
            } else unprocessedViolations.add(constraintViolation);
        }
        if (!unprocessedViolations.isEmpty())
            log.error("Unprocessed constrain violation detected: {}", unprocessedViolations);
        return ResponseEntity.badRequest().body(new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error", errorsDescriptions));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
