package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>, JpaSpecificationExecutor<OrderItem> {

    // Cari semua order item berdasarkan orderId
    List<OrderItem> findByOrderId(String orderId);

    // Cari semua order item berdasarkan menuId
    List<OrderItem> findByMenuId(String menuId);

    // Cari semua order item berdasarkan orderId dan menuId
    List<OrderItem> findByOrderIdAndMenuId(String orderId, String menuId);

}
