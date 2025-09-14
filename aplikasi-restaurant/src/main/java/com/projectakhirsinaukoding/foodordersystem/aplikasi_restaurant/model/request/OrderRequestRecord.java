package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;

import java.time.LocalDateTime;

public record OrderRequestRecord(String id,
                                 String orderNumber,
                                 Double totalAmount,
                                 StatusOrder statusOrder,
                                 String userId,
                                 LocalDateTime orderDate) {
}
