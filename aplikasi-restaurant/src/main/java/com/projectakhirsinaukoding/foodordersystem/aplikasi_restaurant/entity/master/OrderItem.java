package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.app.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "m_order_item", indexes = {
        @Index(name = "idx_order_item_order", columnList = "order_id"),
        @Index(name = "idx_order_item_menu", columnList = "menu_id"),
        @Index(name = "idx_order_item_created_date", columnList = "createdDate"),
        @Index(name = "idx_order_item_modified_date", columnList = "modifiedDate")
})
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    // Relasi ke Order (banyak OrderItem bisa dimiliki 1 Order)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    // Relasi ke Menu (banyak OrderItem bisa merujuk ke 1 Menu)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    private Menu menu;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price; // harga menu saat order (snapshot, biar tidak berubah kalau harga menu berubah / ter-update)

    @Column(nullable = true)
    private Double subtotal; // price * quantity
}
