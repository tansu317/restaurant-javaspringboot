package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;

public record MenuFilterRecord(String name, StatusMenu statusMenu, String category, Double sellingPrice) {
}
