package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    PEMBELI("Pembeli"),
    KASIR("Kasir"),
//    CHEF("Chef"),
    ADMIN("Admin");

    private final String label;

    Role(String label) {
        this.label = label;
    }

}
