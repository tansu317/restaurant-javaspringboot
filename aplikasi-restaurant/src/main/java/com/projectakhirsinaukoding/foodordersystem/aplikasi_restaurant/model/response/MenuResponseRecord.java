package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;

import java.time.LocalDateTime;

public record MenuResponseRecord(String id,
                                 String name,
                                 String description,
                                 Double sellingPrice,
                                 String category,
                                 StatusMenu statusMenu,
                                 LocalDateTime createdDate,
                                 LocalDateTime modifiedDate) {
}
