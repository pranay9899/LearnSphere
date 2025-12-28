package com.learnsphere.app.services;

import com.learnsphere.app.entity.mapper.VerificationToken;
import com.learnsphere.app.entity.mapper.VerificationToken.TokenType;
import com.learnsphere.app.repository.VerificationTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository repo;

    public VerificationTokenServiceImpl(VerificationTokenRepository repo) {
        this.repo = repo;
    }

    @Override
    @Transactional
    public VerificationToken createToken(String email, TokenType type, Instant expiryTime) {
        String token = UUID.randomUUID().toString();
        VerificationToken vt = new VerificationToken(token, email, expiryTime, type);
        return repo.save(vt);
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return repo.findByToken(token);
    }

    @Override
    @Transactional
    public void delete(VerificationToken token) {
        repo.delete(token);
    }

    @Override
    @Transactional
    public void purgeExpired() {
        repo.deleteByExpiryTimeBefore(Instant.now());
    }
}
