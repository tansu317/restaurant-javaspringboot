package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;

public record OrderResponseRecord(String id,
                                  String userId,
                                  String username,
                                  Double totalAmount,
                                  StatusOrder statusOrder) {
}
