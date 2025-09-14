package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.impl;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.builder.CustomBuilder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master.OrderMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.AppPage;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.managementuser.UserRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.OrderRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.OrderService;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    @Override
    public void add(OrderRequestRecord request) {
        validasiMandatory(request);

        User user = userRepository.findById(request.userId())
                  .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        var order = orderMapper.requestToEntity(request, user);
        order.setCreatedBy(user.getNama());
        order.setCreatedDate(LocalDateTime.now());
        order.setUpdatedBy(user.getNama());
        order.setModifiedDate(LocalDateTime.now());
        order.setOrderDate(LocalDateTime.now());

        orderRepository.save(order);
    }

    @Override
    public void edit(OrderRequestRecord request) {
        // validasi mandatory
        validasiMandatory(request);

        var orderExisting = orderRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Data order tidak ditemukan"));

        // kalau perlu validasi orderNumber unik
        if (orderRepository.existsByOrderNumber(request.orderNumber())) {
            throw new RuntimeException("OrderNumber [" + request.orderNumber() + "] sudah digunakan");
        }

        // ambil user id
        var user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("User dengan ID [" + request.userId() + "] tidak ditemukan"));

        var order = orderMapper.requestToEntity(request, user);
        order.setId(orderExisting.getId());

        orderRepository.save(order);
    }

    @Override
    public Page<SimpleMap> findAll(OrderFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Order> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("orderNumber", filterRequest.orderNumber(), builder);
        FilterUtil.builderConditionNotNullEqual("statusOrder", filterRequest.statusOrder(), builder);

        Page<Order> listOrder = orderRepository.findAll(builder.build(), pageable);

        List<SimpleMap> listData = listOrder.stream().map(order -> {
            SimpleMap data = new SimpleMap();
            data.put("id", order.getId());
            data.put("orderNumber", order.getOrderNumber());
            data.put("totalAmount", order.getTotalAmount());
            data.put("statusOrder", order.getStatusOrder().getLabel());
            data.put("user", order.getUser().getNama());
            data.put("orderDate", order.getOrderDate());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listOrder.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", order.getId());
        data.put("orderNumber", order.getOrderNumber());
        data.put("totalAmount", order.getTotalAmount());
        data.put("statusOrder", order.getStatusOrder().getLabel());
        data.put("user", order.getUser().getNama());
        data.put("orderDate", order.getOrderDate());

        return data;
    }

    private void validasiMandatory(OrderRequestRecord request) {
        if (request.totalAmount() == null) {
            throw new RuntimeException("Total amount tidak boleh kosong");
        }
        if (request.userId() == null || request.userId().isEmpty()) {
            throw new RuntimeException("UserId tidak boleh kosong");
        }
    }
}
