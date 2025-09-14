package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.app.BaseEntity;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_order", indexes = {
        @Index(name = "idx_order_created_date", columnList = "createdDate"),
        @Index(name = "idx_order_modified_date", columnList = "modifiedDate"),
        @Index(name = "idx_order_status", columnList = "status")
})
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false) // PEMBELI dengan relasi ManyToOne (bnyak order bsa dimiliki 1 user)
    private User user;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOrder statusOrder;

    @Column(nullable = false)
    private Double totalAmount; // total harga pesanan
}