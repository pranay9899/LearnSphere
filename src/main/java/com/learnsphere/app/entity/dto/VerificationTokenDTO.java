package com.learnsphere.app.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.Instant;

public class VerificationTokenDTO {
    @NotBlank
    @Size(min = 6, max = 255)
    private String token;

    @NotBlank
    private String email;

    @NotNull
    private Instant expiryTime;

    @NotBlank
    private String type;

    public VerificationTokenDTO() {}
    // getters & setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = (token == null) ? null : token.trim(); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = (email == null) ? null : email.trim().toLowerCase(); }
    public Instant getExpiryTime() { return expiryTime; }
    public void setExpiryTime(Instant expiryTime) { this.expiryTime = expiryTime; }
    public String getType() { return type; }
    public void setType(String type) { this.type = (type == null) ? null : type.trim().toUpperCase(); }
}
