package com.maverick.scheduler.service;

import com.maverick.scheduler.dto.UserResponseDTO;
import com.maverick.scheduler.model.User;
import com.maverick.scheduler.model.WorkConfig;
import com.maverick.scheduler.repository.UserRepository;
import com.maverick.scheduler.repository.WorkConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkConfigRepository workConfigRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO create(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);
        return new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), user.getRole());
    }

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole()))
                .collect(Collectors.toList());
    }

    public UserResponseDTO setWorkConfig(Long userId, WorkConfig config) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        config.setUser(user);
        workConfigRepository.save(config);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}