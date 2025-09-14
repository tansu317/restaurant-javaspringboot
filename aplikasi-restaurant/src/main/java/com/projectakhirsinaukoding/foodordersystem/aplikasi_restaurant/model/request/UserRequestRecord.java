package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Role;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Status;

public record UserRequestRecord(String id,
                                String nama,
                                String username,
                                String email,
                                String password,
                                Status status,
                                Role role) {
}
