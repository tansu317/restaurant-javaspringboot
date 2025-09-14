package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response;

public record OrderItemResponseRecord(String id,
                                      String orderId,     // relasi ke Order
                                      String menuId,      // relasi ke Menu
                                      Integer quantity,
                                      Double price) {
}
