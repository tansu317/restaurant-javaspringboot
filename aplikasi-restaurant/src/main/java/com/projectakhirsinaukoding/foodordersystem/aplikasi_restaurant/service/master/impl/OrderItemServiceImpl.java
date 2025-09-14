package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.impl;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.builder.CustomBuilder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.OrderItem;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master.OrderItemMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.AppPage;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderItemFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderItemRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.MenuRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.OrderItemRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.OrderRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.OrderItemService;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final OrderItemMapper orderItemMapper;

    @Override
    public void add(OrderItemRequestRecord request) {
        validasiMandatory(request);

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order tidak ditemukan"));

        Menu menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new RuntimeException("Menu tidak ditemukan"));

        var orderItem = orderItemMapper.requestToEntity(request, order, menu);
        orderItem.setCreatedBy("SYSTEM");
        orderItem.setCreatedDate(LocalDateTime.now());
        orderItem.setUpdatedBy("SYSTEM");
        orderItem.setModifiedDate(LocalDateTime.now());

        orderItemRepository.save(orderItem);
    }

    @Override
    public void edit(OrderItemRequestRecord request) {
        validasiMandatory(request);

        var existing = orderItemRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Order item tidak ditemukan"));

        Order order = orderRepository.findById(request.orderId())
                .orElseThrow(() -> new RuntimeException("Order tidak ditemukan"));

        Menu menu = menuRepository.findById(request.menuId())
                .orElseThrow(() -> new RuntimeException("Menu tidak ditemukan"));

        var orderItem = orderItemMapper.requestToEntity(request, order, menu);
        orderItem.setId(existing.getId());
        orderItem.setCreatedBy(existing.getCreatedBy());
        orderItem.setCreatedDate(existing.getCreatedDate());
        orderItem.setUpdatedBy("SYSTEM");
        orderItem.setModifiedDate(LocalDateTime.now());

        orderItemRepository.save(orderItem);
    }

    @Override
    public Page<SimpleMap> findAll(OrderItemFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<OrderItem> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotNullEqual("order.id", filterRequest.orderId(), builder);
        FilterUtil.builderConditionNotNullEqual("menu.id", filterRequest.menuId(), builder);
        FilterUtil.builderConditionNotNullGreaterThanOrEqual("quantity", filterRequest.minQuantity(), builder);
        FilterUtil.builderConditionNotNullLessThanOrEqual("quantity", filterRequest.maxQuantity(), builder);
        FilterUtil.builderConditionNotNullGreaterThanOrEqual("price", filterRequest.minPrice(), builder);
        FilterUtil.builderConditionNotNullLessThanOrEqual("price", filterRequest.maxPrice(), builder);

        Page<OrderItem> list = orderItemRepository.findAll(builder.build(), pageable);

        List<SimpleMap> listData = list.stream().map(oi -> {
            SimpleMap data = new SimpleMap();
            data.put("id", oi.getId());
            data.put("orderId", oi.getOrder().getId());
            data.put("menuId", oi.getMenu().getId());
            data.put("quantity", oi.getQuantity());
            data.put("price", oi.getPrice());
            data.put("createdDate", oi.getCreatedDate());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, list.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var oi = orderItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order item tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", oi.getId());
        data.put("orderId", oi.getOrder().getId());
        data.put("menuId", oi.getMenu().getId());
        data.put("quantity", oi.getQuantity());
        data.put("price", oi.getPrice());
        data.put("createdDate", oi.getCreatedDate());
        data.put("modifiedDate", oi.getModifiedDate());

        return data;
    }

    private void validasiMandatory(OrderItemRequestRecord request) {
        if (request.orderId() == null || request.orderId().isEmpty()) {
            throw new RuntimeException("OrderId tidak boleh kosong");
        }
        if (request.menuId() == null || request.menuId().isEmpty()) {
            throw new RuntimeException("MenuId tidak boleh kosong");
        }
        if (request.quantity() == null || request.quantity() <= 0) {
            throw new RuntimeException("Quantity harus lebih dari 0");
        }
        if (request.price() == null || request.price() <= 0) {
            throw new RuntimeException("Harga harus lebih dari 0");
        }
    }
}
