package com.pismo.txnroutine.exceptions;

public enum ApiErrors {
    DOCUMENT_NO_ALREADY_EXIST("ACC-001", "Document No already exists : %s"),
    ACCOUNT_NOT_FOUND("ACC-002", "Account not found : %s");
    
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