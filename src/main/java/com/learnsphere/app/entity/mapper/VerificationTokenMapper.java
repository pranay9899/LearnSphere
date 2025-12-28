package com.learnsphere.app.entity.mapper;

import com.learnsphere.app.entity.dto.VerificationTokenDTO;

public final class VerificationTokenMapper {
    private VerificationTokenMapper() {}

    public static VerificationToken toEntity(VerificationTokenDTO dto) {
        if (dto == null) return null;
        VerificationToken e = new VerificationToken();
        e.setToken(dto.getToken());
        e.setEmail(dto.getEmail());
        e.setExpiryTime(dto.getExpiryTime());
        e.setType(VerificationToken.TokenType.valueOf(dto.getType()));
        return e;
    }

    public static VerificationTokenDTO toDto(VerificationToken e) {
        if (e == null) return null;
        VerificationTokenDTO dto = new VerificationTokenDTO();
        dto.setToken(e.getToken());
        dto.setEmail(e.getEmail());
        dto.setExpiryTime(e.getExpiryTime());
        dto.setType(e.getType().name());
        return dto;
    }
}
