package com.pismo.txnroutine.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
    private final Object[] args;

    public ApplicationException(ApiErrors apiError, Object... args) {
        super(String.format(apiError.getMessage(), args));
        this.errorCode = apiError.getCode();
        this.message = apiError.getMessage();
        this.args = args;
        this.httpStatus = HttpStatus.CONFLICT;
    }
}