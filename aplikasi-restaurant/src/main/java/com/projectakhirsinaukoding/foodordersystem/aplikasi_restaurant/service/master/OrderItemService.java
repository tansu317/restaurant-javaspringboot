package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.MenuFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderItemFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.MenuRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderItemRequestRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderItemService {

    void add(OrderItemRequestRecord request);

    void edit(OrderItemRequestRecord request);

    Page<SimpleMap> findAll(OrderItemFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);
}
