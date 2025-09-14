package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;

public record OrderFilterRecord(String orderNumber,
                                StatusOrder statusOrder,
                                String userId) {
}
