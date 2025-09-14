package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.OrderItem;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderItemRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    public OrderItem requestToEntity(OrderItemRequestRecord request, Order order, Menu menu) {
        Integer qty = request.quantity() == null ? 0 : request.quantity();

        // price = harga satuan. Jika request memberikan price, anggap itu harga satuan.
        Double unitPrice = (request.price() != null)
                ? request.price()
                : (menu != null && menu.getSellingPrice() != null ? menu.getSellingPrice() : 0.0);

        Double subtotal = unitPrice * qty;

        return OrderItem.builder()
                .order(order)
                .menu(menu)
                .quantity(qty)
                .price(unitPrice)   // harga satuan (snapshot, harga di menu berubah disini tidak berubah)
                .subtotal(subtotal) // harga satuan * quantity
                .build();
    }
}
