package com.felipearaujo.bank_santander.domain.service.exception;

public class NotFoundException extends BusinessException{

    public NotFoundException() {
        super("Resource not found.");
    }
    
}
