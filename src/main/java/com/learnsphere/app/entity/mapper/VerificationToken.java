package com.learnsphere.app.entity.mapper;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String token;

    @NotBlank
    @Column(nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private Instant expiryTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TokenType type;

    @CreationTimestamp
    private Instant createdAt;

    public VerificationToken() {}

    public VerificationToken(String token, String email, Instant expiryTime, TokenType type) {
        this.token = token;
        this.email = email;
        this.expiryTime = expiryTime;
        this.type = type;
    }

    // getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = (token == null) ? null : token.trim(); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = (email == null) ? null : email.trim().toLowerCase(); }

    public Instant getExpiryTime() { return expiryTime; }
    public void setExpiryTime(Instant expiryTime) { this.expiryTime = expiryTime; }

    public TokenType getType() { return type; }
    public void setType(TokenType type) { this.type = type; }

    public Instant getCreatedAt() { return createdAt; }

    public enum TokenType { VERIFY_EMAIL, RESET_PASSWORD, TWO_FACTOR, ACCOUNT_ACTIVATION }
}
