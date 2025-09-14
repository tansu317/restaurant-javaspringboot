package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.impl;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.builder.CustomBuilder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.master.Menu;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.master.MenuMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.AppPage;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.MenuFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.MenuRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.master.MenuRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.master.MenuService;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final MenuMapper menuMapper;

    @Override
    public void add(MenuRequestRecord request) {
        validasiMandatory(request);

        // Cek duplikat nama menu
        if (menuRepository.existsByName(request.name())) {
            throw new RuntimeException("Menu dengan nama [" + request.name() + "] sudah ada");
        }

        var menu = menuMapper.requestToEntity(request);
        menu.setCreatedBy("SYSTEM");
        menu.setCreatedDate(LocalDateTime.now());
        menu.setUpdatedBy("SYSTEM");
        menu.setModifiedDate(LocalDateTime.now());

        menuRepository.save(menu);
    }

    @Override
    public void edit(MenuRequestRecord request) {
        validasiMandatory(request);

        var menuExisting = menuRepository.findById(request.id())
                .orElseThrow(() -> new RuntimeException("Data menu tidak ditemukan"));

        // kalau nama berubah, validasi unik
        if (!menuExisting.getName().equalsIgnoreCase(request.name())
                && menuRepository.existsByName(request.name())) {
            throw new RuntimeException("Menu dengan nama [" + request.name() + "] sudah digunakan");
        }

        var menu = menuMapper.requestToEntity(request);
        menu.setId(menuExisting.getId());
        menu.setCreatedBy(menuExisting.getCreatedBy());
        menu.setCreatedDate(menuExisting.getCreatedDate());
        menu.setUpdatedBy("SYSTEM");
        menu.setModifiedDate(LocalDateTime.now());

        menuRepository.save(menu);
    }

    @Override
    public Page<SimpleMap> findAll(MenuFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<Menu> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("name", filterRequest.name(), builder);
        FilterUtil.builderConditionNotBlankLike("category", filterRequest.category(), builder);
        FilterUtil.builderConditionNotNullEqual("statusMenu", filterRequest.statusMenu(), builder);

        Page<Menu> listMenu = menuRepository.findAll(builder.build(), pageable);

        List<SimpleMap> listData = listMenu.stream().map(menu -> {
            SimpleMap data = new SimpleMap();
            data.put("id", menu.getId());
            data.put("name", menu.getName());
            data.put("price", menu.getSellingPrice());
            data.put("category", menu.getCategory());
            data.put("statusMenu", menu.getStatusMenu());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listMenu.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu tidak ditemukan"));

        SimpleMap data = new SimpleMap();
        data.put("id", menu.getId());
        data.put("name", menu.getName());
        data.put("description", menu.getDescription());
        data.put("price", menu.getSellingPrice());
        data.put("category", menu.getCategory());
        data.put("statusMenu", menu.getStatusMenu());
        data.put("createdDate", menu.getCreatedDate());
        data.put("modifiedDate", menu.getModifiedDate());

        return data;
    }

    private void validasiMandatory(MenuRequestRecord request) {
        if (request.name() == null || request.name().isEmpty()) {
            throw new RuntimeException("Nama menu tidak boleh kosong");
        }
        if (request.sellingPrice() == null) {
            throw new RuntimeException("Harga menu tidak boleh kosong");
        }
        if (request.category() == null || request.category().isEmpty()) {
            throw new RuntimeException("Kategori menu tidak boleh kosong");
        }
        if (request.statusMenu() == null) {
            throw new RuntimeException("Status menu tidak boleh kosong");
        }
    }
}