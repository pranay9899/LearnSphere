package com.learnsphere.app.repository;

import com.learnsphere.app.entity.mapper.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.Instant;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    void deleteByExpiryTimeBefore(Instant now);
    Optional<VerificationToken> findByEmailAndType(String email, VerificationToken.TokenType type);
}
