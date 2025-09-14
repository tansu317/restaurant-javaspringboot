package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.controller.master;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.OrderItemFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.OrderItemRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.BaseResponse;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order-item")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> save(@RequestBody OrderItemRequestRecord request) {
        orderItemService.add(request);
        return BaseResponse.ok("Data order item berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> edit(@RequestBody OrderItemRequestRecord request) {
        orderItemService.edit(request);
        return BaseResponse.ok("Data order item berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody OrderItemFilterRecord filterRequest) {
        return BaseResponse.ok(null, orderItemService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, orderItemService.findById(id));
    }
}