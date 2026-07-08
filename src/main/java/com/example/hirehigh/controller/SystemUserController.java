package com.example.hirehigh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hirehigh.dto.LoginRequest;
import com.example.hirehigh.entity.SystemUser;
import com.example.hirehigh.security.JwtUtil;
import com.example.hirehigh.service.SystemUserService;

@RestController
@RequestMapping("/hirehigh")
public class SystemUserController {

    @Autowired
    private SystemUserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/registers")
    public ResponseEntity<SystemUser> register(@RequestBody SystemUser user) {

        // Password encoding is already done in the service
        SystemUser saved = service.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        SystemUser user;

        try {
            user = service.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername());

        return ResponseEntity.ok(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<SystemUser>> getAllUsers() {
        return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<SystemUser> getById(@PathVariable Long id) {

        try {
            SystemUser user = service.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<SystemUser> update(
            @PathVariable Long id,
            @RequestBody SystemUser user) {

        return ResponseEntity.ok(service.updateUser(id, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully");
    }
  @PatchMapping("/users/{id}")
public ResponseEntity<SystemUser> patchUpdate(
        @PathVariable Long id,
        @RequestBody SystemUser user) {

    return ResponseEntity.ok(service.patchUser(id, user));
}
}