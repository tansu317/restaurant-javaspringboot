package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Role;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Status;

public record UserResponseRecord(String id,
                                 String nama,
                                 String username,
                                 String email,
                                 Status status,
                                 Role role) {
}
