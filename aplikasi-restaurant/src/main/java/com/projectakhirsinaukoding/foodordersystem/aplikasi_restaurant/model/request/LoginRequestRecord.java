package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestRecord(@NotBlank String username,
                                 @NotBlank String password) {
}
