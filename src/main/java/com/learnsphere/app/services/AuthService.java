package com.learnsphere.app.services;

import com.learnsphere.app.entity.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    void verifyEmail(String token);
}
