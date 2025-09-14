package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.controller.app;


import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.config.UserLoggedInConfig;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.LoginRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.BaseResponse;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public BaseResponse<?> login(@RequestBody LoginRequestRecord request) {
        return BaseResponse.ok(null, authService.login(request));
    }

    @GetMapping("logout")
    public BaseResponse<?> logout(@AuthenticationPrincipal UserLoggedInConfig userLoggedInConfig) {
        var userLoggedIn = userLoggedInConfig.getUser();
        authService.logout(userLoggedIn);
        return BaseResponse.ok("Berhasil logout", null);
    }

}
