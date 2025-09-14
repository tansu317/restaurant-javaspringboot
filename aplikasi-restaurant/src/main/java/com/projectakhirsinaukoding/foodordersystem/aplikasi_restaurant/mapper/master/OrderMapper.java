package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public Order requestToEntity(OrderRequestRecord request, User user) {
        String orderNumber = request.orderNumber() != null && !request.orderNumber().isBlank()
                ? request.orderNumber().toUpperCase()
                : generateOrderNumber();

        StatusOrder status = request.statusOrder() != null ? request.statusOrder() : StatusOrder.BARU;

        return Order.builder()
                .orderNumber(request.orderNumber().toUpperCase())
                .statusOrder(request.statusOrder() != null ? request.statusOrder() : StatusOrder.BARU)
                .totalAmount(request.totalAmount())
                .user(user) // relasi ManyToOne
                .build();
    }

    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
