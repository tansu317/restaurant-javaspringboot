package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.OrderResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    void add(OrderRequestRecord request);

    void edit(OrderRequestRecord request);

    Page<SimpleMap> findAll(OrderFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
