package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusMenu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.MenuRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class MenuMapper {

    public Menu requestToEntity(MenuRequestRecord request) {
        return Menu.builder()
                .name(request.name() != null ? request.name().trim() : null)
                .description(request.description())
                .sellingPrice(request.sellingPrice())
                .category(request.category() != null ? request.category().trim() : null)
                .statusMenu(request.statusMenu() != null ? request.statusMenu() : StatusMenu.TERSEDIA)
                .build();
    }
}
