package com.paulopsms.idp_authenticator.domain.exceptions;

public class BusinessRuntimeException extends RuntimeException {
    public BusinessRuntimeException(Throwable e) {
        super(e);
    }
}
