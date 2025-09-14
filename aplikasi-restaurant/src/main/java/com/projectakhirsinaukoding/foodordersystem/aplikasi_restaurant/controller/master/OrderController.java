package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.controller.master;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.BaseResponse;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> save(@RequestBody OrderRequestRecord request) {
        orderService.add(request);
        return BaseResponse.ok("Data order berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> edit(@RequestBody OrderRequestRecord request) {
        orderService.edit(request);
        return BaseResponse.ok("Data order berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody OrderFilterRecord filterRequest) {
        return BaseResponse.ok(null, orderService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, orderService.findById(id));
    }
}
