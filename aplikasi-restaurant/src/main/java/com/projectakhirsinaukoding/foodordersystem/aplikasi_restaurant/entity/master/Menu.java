package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.app.BaseEntity;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;
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
@Table(name = "m_menu", indexes = {
        @Index(name = "idx_menu_name", columnList = "name"),
        @Index(name = "idx_menu_category", columnList = "category"),
        @Index(name = "idx_menu_created_date", columnList = "createdDate"),
        @Index(name = "idx_menu_modified_date", columnList = "modifiedDate"),
})
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Double sellingPrice;

    @Column(nullable = false)
    private String category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusMenu statusMenu;
}
