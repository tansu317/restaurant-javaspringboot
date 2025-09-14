package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Order;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master.OrderMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.StatusOrder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.managementuser.UserRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.OrderRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app.ValidatorService;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void testAddOrder_Success() {
        // given
        var request = new OrderRequestRecord(
                null,
                "ORD-123",
                150000.0,
                StatusOrder.BARU,
                "c13c1d62-b3fc-4989-875a-ddceaf61f076",
                LocalDateTime.now()
        );

        var userEntity = new User(); // mock user relasi
        var orderEntity = new Order();

        when(userRepository.findById(request.userId())).thenReturn(Optional.of(userEntity));
        when(orderMapper.requestToEntity(request, userEntity)).thenReturn(orderEntity);

        // when
        orderService.add(request);

        // then
        verify(validatorService, times(1)).validator(request);
        verify(userRepository, times(1)).findById(request.userId());
        verify(orderMapper, times(1)).requestToEntity(request, userEntity);
        verify(orderRepository, times(1)).save(orderEntity);
    }
}
