package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request;

public record OrderItemRequestRecord(String id,
                                     String orderId,     // relasi ke Order
                                     String menuId,      // relasi ke Menu
                                     Integer quantity,
                                     Double price ) {
}
