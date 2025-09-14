package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.app.impl;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.config.UserLoggedInConfig;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.Checks;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.managementuser.UserRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserLoggedInServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User : " + username + " tidak ditemukan"));
        Checks.isTrue(StringUtils.isNotBlank(user.getToken()), "Session habis, silahkan login kembali");
        return new UserLoggedInConfig(user);
    }

}
