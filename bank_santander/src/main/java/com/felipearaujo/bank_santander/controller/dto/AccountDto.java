package com.felipearaujo.bank_santander.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.Account;

public record AccountDto(UUID id, String number, String agency, BigDecimal balance, BigDecimal limit) {
    
    public AccountDto(Account model) {
        this(model.getUuid(), model.getNumber(), model.getAgency(), model.getBalance(), model.getLimit());
    }

    public Account toModel(){
        Account model = new Account();
        model.setUuid(this.id);
        model.setNumber(this.number);
        model.setAgency(this.agency);
        model.setBalance(this.balance);
        model.setLimit(this.limit);
        return model;
    }
}
