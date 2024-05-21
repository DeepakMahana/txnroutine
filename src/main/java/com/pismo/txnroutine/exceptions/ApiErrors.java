package com.pismo.txnroutine.exceptions;

public enum ApiErrors {
    
    DOCUMENT_NO_ALREADY_EXIST("ACC-001", "Document No already exists"),
    ACCOUNT_NOT_FOUND("ACC-002", "Account not found"),
    OPERATIONTYPE_NOT_FOUND("ACC-003", "Operation Type not found");
    
    private final String code;
    private final String message;

    ApiErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}