package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.controller.master;


import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.MenuFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.MenuRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.BaseResponse;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> save(@RequestBody MenuRequestRecord request) {
        menuService.add(request);
        return BaseResponse.ok("Data menu berhasil disimpan", null);
    }

    @PostMapping("edit")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR')")
    public BaseResponse<?> edit(@RequestBody MenuRequestRecord request) {
        menuService.edit(request);
        return BaseResponse.ok("Data menu berhasil diubah", null);
    }

    @PostMapping("find-all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> findAll(@PageableDefault(direction = Sort.Direction.DESC, sort = "modifiedDate") Pageable pageable,
                                   @RequestBody MenuFilterRecord filterRequest) {
        return BaseResponse.ok(null, menuService.findAll(filterRequest, pageable));
    }

    @GetMapping("find-by-id/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KASIR') or hasRole('PEMBELI')")
    public BaseResponse<?> findById(@PathVariable String id) {
        return BaseResponse.ok(null, menuService.findById(id));
    }
}
