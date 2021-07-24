package com.hoon.springbestpractice.global.error.model;

import com.hoon.springbestpractice.global.error.ErrorCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import lombok.Getter;


/**
 * reference: https://github.com/cheese10yun/spring-guide/blob/master/src/main/java/com/spring/guide/global/error/GlobalExceptionHandler.java
 */

@Getter
@NoArgsConstructor
public class ErrorResponse {

    private int status;

    private String message;

    private List<FieldError> errors;

    public ErrorResponse(final ErrorCode errorCode) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = new ArrayList<>();;
    }

    public ErrorResponse(final ErrorCode errorCode, final BindingResult bindingResult) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = FieldError.of(bindingResult);
    }

    public ErrorResponse(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());

        setErrorResponseOf(ErrorCode.INVALID_TYPE_VALUE, errors);
    }

    private void setErrorResponseOf(final ErrorCode errorCode, final List<FieldError> errors) {
        this.message = errorCode.getMessage();
        this.status = errorCode.getStatus();
        this.errors = errors;
    }

    @Getter
    @NoArgsConstructor
    public static class FieldError {
        private String field;

        private String value;

        private String reason;

        private FieldError(
                final String field,
                final String value,
                final String reason
        ) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldError> of(
                final String field,
                final String value,
                final String reason
        ) {
            List<FieldError> fieldErrors = new ArrayList<>();
            fieldErrors.add(new FieldError(field, value, reason));
            return fieldErrors;
        }

        private static List<FieldError> of(final BindingResult bindingResult) {
            final List<org.springframework.validation.FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new FieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
