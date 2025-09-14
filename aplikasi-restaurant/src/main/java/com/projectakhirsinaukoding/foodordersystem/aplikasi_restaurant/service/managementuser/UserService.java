package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.managementuser;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.UserFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.UserRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.UserResponseRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    void add(UserRequestRecord request);

    void edit(UserRequestRecord request);

    UserResponseRecord register(UserRequestRecord request);

    Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable);

    SimpleMap findById(String id);

}
