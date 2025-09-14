package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant;


import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master.OrderMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Role;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Status;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.managementuser.UserRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.OrderRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.impl.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    private User user;
    private Order order;
    private OrderRequestRecord request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user = User.builder()
                .id("user-1")
                .nama("Budi")
                .username("budi")
                .email("budi@mail.com")
                .password("secret")
                .role(Role.PEMBELI)
                .status(Status.AKTIF)
                .build();

        order = Order.builder()
                .id("order-1")
                .orderNumber("ORD-001")
                .statusOrder(StatusOrder.BARU)
                .totalAmount(Double.valueOf(100000))
                .user(user)
                .build();

        request = new OrderRequestRecord(
                "order-1",
                "ORD-001",
                100000.0,
                StatusOrder.BARU,
                "user-123",
                LocalDateTime.now()
        );
    }

    @Test
    void testAddOrderSuccess() {
        when(userRepository.findById("user-1")).thenReturn(Optional.of(user));
        when(orderMapper.requestToEntity(request, user)).thenReturn(order);

        orderService.add(request);

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testEditOrderSuccess() {
        when(orderRepository.findById("order-1")).thenReturn(Optional.of(order));
        when(userRepository.findById("user-1")).thenReturn(Optional.of(user));
        when(orderMapper.requestToEntity(request, user)).thenReturn(order);
        when(orderRepository.existsByOrderNumber("ORD-001")).thenReturn(false);

        orderService.edit(request);

        verify(orderRepository, times(1)).save(order);
    }

//    @Test
//    void testFindByIdSuccess() {
//        when(orderRepository.findById("order-1")).thenReturn(Optional.of(order));
//
//        var result = orderService.findById("order-1");
//
//        assertNotNull(result);
//        assertEquals("ORD-001", result.getOrderNumber());
//    }

//    @Test
//    void testFindAllOrders() {
//        var pageable = PageRequest.of(0, 10);
//        OrderFilterRecord filter = new OrderFilterRecord("ORD-001", null, null);
//
//        Page<Order> page = new PageImpl<>(List.of(order));
//        when(orderRepository.findAll(any(), eq(pageable))).thenReturn(page);
//
//        var result = orderService.findAll(filter, pageable);
//
//        assertEquals(1, result.getContent().size());
//        assertEquals("ORD-001", result.getContent().get(0).getOrderNumber());
//    }
}
