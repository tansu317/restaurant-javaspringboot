package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.managementuser;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.UserRequestRecord;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToEntity(UserRequestRecord request) {
        return User.builder()
                .nama(request.nama().toUpperCase())
                .username(request.username().toLowerCase())
                .email(request.email().toLowerCase())
                .status(request.status())
                .role(request.role())
                .build();
    }

}
