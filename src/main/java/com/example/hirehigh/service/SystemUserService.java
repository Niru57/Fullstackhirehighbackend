package com.example.hirehigh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.hirehigh.entity.SystemUser;
import com.example.hirehigh.repository.SystemUserRepository;

@Service
public class SystemUserService {

    @Autowired
    private SystemUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    public SystemUser register(SystemUser user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    
    public SystemUser findByUsername(String username) {

        return repository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

  
    public List<SystemUser> getAllUsers() {

        return repository.findAll();
    }

    
    public SystemUser getUserById(Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    
    public SystemUser updateUser(Long id, SystemUser user) {

        SystemUser existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existing.setUsername(user.getUsername());
        existing.setEmail(user.getEmail());
        existing.setRole(user.getRole());
        existing.setFullName(user.getFullName());

        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }

        return repository.save(existing);
    }


    public void deleteUser(Long id) {

    SystemUser user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

    repository.delete(user);
}


    public SystemUser patchUser(Long id, SystemUser user) {

        SystemUser existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getUsername() != null)
            existing.setUsername(user.getUsername());

        if (user.getEmail() != null)
            existing.setEmail(user.getEmail());

        if (user.getPassword() != null && !user.getPassword().isEmpty())
            existing.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRole() != null)
            existing.setRole(user.getRole());

        if (user.getFullName() != null)
            existing.setFullName(user.getFullName());

        return repository.save(existing);
    }
}