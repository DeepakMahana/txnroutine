package com.pismo.txnroutine.exceptions;

public enum ApiErrors {
    
    DOCUMENT_NO_ALREADY_EXIST("ACC-001", "Document No already exists", 409),
    ACCOUNT_NOT_FOUND("ACC-002", "Account not found", 404),
    OPERATIONTYPE_NOT_FOUND("ACC-003", "Operation Type not found", 404);
    
    private final String code;
    private final String message;
    private final int httpStatus;


    ApiErrors(String code, String message, int httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}