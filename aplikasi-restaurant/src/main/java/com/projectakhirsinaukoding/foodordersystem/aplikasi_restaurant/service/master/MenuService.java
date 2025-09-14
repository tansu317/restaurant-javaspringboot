package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.MenuFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.MenuRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MenuService {

    void add(MenuRequestRecord request);

    void edit(MenuRequestRecord request);

    Page<SimpleMap> findAll(MenuFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);
}
