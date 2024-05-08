package com.felipearaujo.bank_santander.controller.dto;

import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.News;

public record NewsDto(UUID id, String icon, String description) {

    public NewsDto(News model) {
        this(model.getUuid(), model.getIcon(), model.getDescription());
    }

    public News toModel() {
        News model = new News();
        model.setUuid(this.id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}
