package com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.managementuser.impl;

import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.builder.CustomBuilder;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.entity.managementuser.User;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.mapper.managementuser.UserMapper;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.AppPage;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.app.SimpleMap;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Role;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.enums.Status;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.filter.UserFilterRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.request.UserRequestRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.model.response.UserResponseRecord;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.repository.managementuser.UserRepository;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.service.managementuser.UserService;
import com.projectakhirsinaukoding.foodordersystem.aplikasi_restaurant.util.FilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void add(UserRequestRecord request) {
        // validasi mandatory
        validasiMandatory(request);

        // validasi data existing
        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public void edit(UserRequestRecord request) {
        // validasi mandatory
        validasiMandatory(request);

        var userExisting = userRepository.findById(request.id()).orElseThrow(() ->  new RuntimeException("Data user tidak ditemukan"));

        // validasi data existing
        if (userRepository.existsByEmailAndIdNot(request.email().toLowerCase(), request.id())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (userRepository.existsByUsernameAndIdNot(request.username().toLowerCase(),  request.id())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        var user = userMapper.requestToEntity(request);
        user.setId(userExisting.getId());
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
    }

    @Override
    public UserResponseRecord register(UserRequestRecord request) {
        // validasi mandatory basic (hanya untuk register)
        if (request.nama() == null || request.nama().isEmpty()) {
            throw new RuntimeException("Nama tidak boleh kosong");
        }
        if (request.username() == null || request.username().isEmpty()) {
            throw new RuntimeException("Username tidak boleh kosong");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Password tidak boleh kosong");
        }

        // validasi data existing
        if (userRepository.existsByEmail(request.email().toLowerCase())) {
            throw new RuntimeException("Email [" + request.email() + "] sudah digunakan");
        }
        if (userRepository.existsByUsername(request.username().toLowerCase())) {
            throw new RuntimeException("Username [" + request.username() + "] sudah digunakan");
        }

        // mapping entity
        var user = userMapper.requestToEntity(request);
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setStatus(Status.AKTIF);   // default status
        user.setRole(Role.ADMIN);         // default role

        user.setCreatedBy("SYSTEM");
        user.setCreatedDate(LocalDateTime.now());
        user.setModifiedDate(LocalDateTime.now());
        user.setUpdatedBy("SYSTEM");

        var saved = userRepository.save(user);

        // mapping ke response (tanpa password)
        return new UserResponseRecord(
                saved.getId(),
                saved.getNama(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getStatus(),
                saved.getRole()
        );
    }


    @Override
    public Page<SimpleMap> findAll(UserFilterRecord filterRequest, Pageable pageable) {
        CustomBuilder<User> builder = new CustomBuilder<>();

        FilterUtil.builderConditionNotBlankLike("nama", filterRequest.nama(), builder);
        FilterUtil.builderConditionNotBlankLike("email", filterRequest.email(), builder);
        FilterUtil.builderConditionNotBlankLike("username", filterRequest.username(), builder);
        FilterUtil.builderConditionNotNullEqual("status", filterRequest.status(), builder);
        FilterUtil.builderConditionNotNullEqual("role", filterRequest.role(), builder);

        Page<User> listUser = userRepository.findAll(builder.build(), pageable);
        List<SimpleMap> listData = listUser.stream().map(user -> {
            SimpleMap data = new SimpleMap();
            data.put("id", user.getId());
            data.put("nama", user.getNama());
            data.put("username", user.getUsername());
            data.put("email", user.getEmail());
            data.put("role", user.getRole().getLabel());
            data.put("status", user.getStatus().getLabel());
            return data;
        }).toList();

        return AppPage.create(listData, pageable, listUser.getTotalElements());
    }

    @Override
    public SimpleMap findById(String id) {
        var user = userRepository.findById(id).orElseThrow(() ->  new RuntimeException("Data user tidak ditemukan"));
        SimpleMap data = new SimpleMap();
        data.put("id", user.getId());
        data.put("nama", user.getNama());
        data.put("username", user.getUsername());
        data.put("email", user.getEmail());
        data.put("status", user.getStatus().getLabel());
        data.put("role", user.getRole().getLabel());
        return data;
    }

    private void validasiMandatory(UserRequestRecord request) {
        if (request.nama() == null || request.nama().isEmpty()) {
            throw new RuntimeException("Nama tidak boleh kosong");
        }
        if (request.username() == null || request.username().isEmpty()) {
            throw new RuntimeException("Username tidak boleh kosong");
        }
        if (request.email() == null || request.email().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.password() == null || request.password().isEmpty()) {
            throw new RuntimeException("Email tidak boleh kosong");
        }
        if (request.status() == null) {
            throw new RuntimeException("Status tidak boleh kosong");
        }
        if (request.role() == null) {
            throw new RuntimeException("Role tidak boleh kosong");
        }
    }

}
