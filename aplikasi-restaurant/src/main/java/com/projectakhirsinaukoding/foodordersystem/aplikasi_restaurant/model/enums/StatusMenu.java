package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums;

import lombok.Getter;

@Getter
public enum StatusMenu {

    TERSEDIA("Tersedia"),
    TIDAK_TERSEDIA("Tidak Tersedia");

    private final String label;

    StatusMenu(String label) {
        this.label = label;
    }
}
