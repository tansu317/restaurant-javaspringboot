package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Role;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Status;

public record UserFilterRecord(String nama,
                               String email,
                               String username,
                               Status status,
                               Role role) {
}
