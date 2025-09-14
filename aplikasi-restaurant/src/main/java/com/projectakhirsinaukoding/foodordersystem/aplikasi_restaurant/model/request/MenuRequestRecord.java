package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;

public record MenuRequestRecord(
                                String id,
                                String name,
                                String description,
                                Double sellingPrice,
                                String category,
                                StatusMenu statusMenu) {
}
