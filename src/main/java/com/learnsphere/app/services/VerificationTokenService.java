package com.learnsphere.app.services;

import com.learnsphere.app.entity.mapper.VerificationToken;
import com.learnsphere.app.entity.mapper.VerificationToken.TokenType;

import java.time.Instant;
import java.util.Optional;

public interface VerificationTokenService {
    VerificationToken createToken(String email, TokenType type, Instant expiryTime);
    Optional<VerificationToken> findByToken(String token);
    void delete(VerificationToken token);
    void purgeExpired();
}
