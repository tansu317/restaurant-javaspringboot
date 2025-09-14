package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums;

import lombok.Getter;

@Getter
public enum StatusOrder {

    BARU("Baru"),
    DIPROSES("Diproses"),
    SELESAI("Selesai"),
    DIBATALKAN("Dibatalkan");

    private final String label;

    StatusOrder(String label) {
        this.label = label;
    }

}
