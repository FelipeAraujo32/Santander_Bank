package com.felipearaujo.bank_santander.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.Card;

public record CardDto(UUID id, String number, BigDecimal limit) {

    public CardDto(Card model) {
        this(model.getUuid(), model.getNumber(), model.getLimit());
    }

    public Card toModel() {
        Card model = new Card();
        model.setUuid(this.id);
        model.setNumber(this.number);
        model.setLimit(this.limit);
        return model;
    }
}
