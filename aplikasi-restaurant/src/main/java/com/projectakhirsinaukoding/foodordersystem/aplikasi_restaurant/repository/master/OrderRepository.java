package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

    // Cek apakah order dengan nomor tertentu sudah ada
    Boolean existsByOrderNumber(String orderNumber);

    // Cari order berdasarkan nomor
    Optional<Order> findByOrderNumber(String orderNumber);

    // Cari semua order milik 1 user
    List<Order> findByUserId(String userId);

    // Cari order berdasarkan status
    List<Order> findByStatusOrder(StatusOrder statusOrder);

    // Cari order berdasarkan status dan user
    List<Order> findByUserIdAndStatusOrder(String userId, StatusOrder statusOrder);
}
