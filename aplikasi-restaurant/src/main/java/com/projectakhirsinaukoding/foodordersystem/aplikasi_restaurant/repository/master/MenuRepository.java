package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, String>, JpaSpecificationExecutor<Menu> {

    // Cari menu berdasarkan nama (exact match)
    Optional<Menu> findByName(String name);

    // Cari semua menu berdasarkan kategori
    List<Menu> findByCategory(String category);

    // Cari semua menu yang statusnya tersedia/tidak tersedia
    List<Menu> findByStatusMenu(StatusMenu statusMenu);

    // Cari menu berdasarkan nama yang mirip (LIKE %name%)
    List<Menu> findByNameContainingIgnoreCase(String name);

    // Cek apakah sudah ada menu dengan nama tertentu
    Boolean existsByName(String name);
}
