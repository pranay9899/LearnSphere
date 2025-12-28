package com.learnsphere.app.services;

import com.learnsphere.app.entity.dto.UserDto;
import com.learnsphere.app.entity.dto.RegisterRequest;
import java.util.Optional;

public interface UserService {
    UserDto register(RegisterRequest req);
    Optional<UserDto> findByEmail(String email);
    void recordLogin(String email);
    void changePasswordWithRaw(String email, String rawPassword);
}
