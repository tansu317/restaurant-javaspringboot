package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.LoginRequestRecord;

public interface AuthService {

    SimpleMap login(LoginRequestRecord request);

    void logout(User userLoggedIn);

}
