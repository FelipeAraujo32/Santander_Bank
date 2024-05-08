package com.felipearaujo.bank_santander.controller.dto;

import java.util.UUID;

import com.felipearaujo.bank_santander.domain.model.Feature;

public record FeatureDto(UUID id, String icon, String description) {

    public FeatureDto(Feature model) {
        this(model.getUuid(), model.getIcon(), model.getDescription());
    }

    public Feature toModel() {
        Feature model = new Feature();
        model.setUuid(this.id);
        model.setIcon(this.icon);
        model.setDescription(this.description);
        return model;
    }
}
