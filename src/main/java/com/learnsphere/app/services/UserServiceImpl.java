package com.learnsphere.app.services;

import com.learnsphere.app.entity.User;
import com.learnsphere.app.entity.User.Role;
import com.learnsphere.app.entity.User.Status;
import com.learnsphere.app.entity.dto.UserDto;
import com.learnsphere.app.entity.dto.RegisterRequest;
import com.learnsphere.app.entity.mapper.UserMapper;
import com.learnsphere.app.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserDto register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email already registered");
        }
        User u = new User();
        u.setUserName(req.getUserName());
        u.setDisplayName(req.getDisplayName());
        u.setEmail(req.getEmail());
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setRole(Role.STUDENT);
        u.setStatus(Status.PENDING);
        u = userRepository.save(u);
        return UserMapper.toDto(u);
    }

    @Override
    public Optional<UserDto> findByEmail(String email) {
        return userRepository.findByEmail(email).map(UserMapper::toDto);
    }

    @Override
    @Transactional
    public void recordLogin(String email) {
        userRepository.findByEmail(email).ifPresent(u -> {
            u.setLastLoginAt(java.time.Instant.now());
            u.setStatus(Status.ACTIVE);
            userRepository.save(u);
        });
    }

    @Override
    @Transactional
    public void changePasswordWithRaw(String email, String rawPassword) {
        userRepository.findByEmail(email).ifPresent(u -> {
            u.setPassword(passwordEncoder.encode(rawPassword));
            userRepository.save(u);
        });
    }
}
