package com.learnsphere.app.entity.mapper;

import com.learnsphere.app.entity.User;
import com.learnsphere.app.entity.dto.UserDto;

public final class UserMapper {
    private UserMapper() {}

    public static UserDto toDto(User u) {
        if (u == null) return null;
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setUserName(u.getUserName());
        dto.setDisplayName(u.getDisplayName());
        dto.setRole(u.getRole() == null ? null : u.getRole().name());
        dto.setEmail(u.getEmail());
        dto.setPhoneNumber(u.getPhoneNumber());
        dto.setGender(u.getGender());
        dto.setDateOfBirth(u.getDateOfBirth());
        dto.setStatus(u.getStatus() == null ? null : u.getStatus().name());
        dto.setPictureUrl(u.getPictureUrl());
        dto.setLocale(u.getLocale());
        dto.setOauthProvider(u.getOauthProvider());
        return dto;
    }

    public static User toEntity(UserDto dto) {
        if (dto == null) return null;
        User u = new User();
        u.setUserName(dto.getUserName());
        u.setDisplayName(dto.getDisplayName());
        if (dto.getRole() != null) u.setRole(User.Role.valueOf(dto.getRole()));
        u.setEmail(dto.getEmail());
        u.setPhoneNumber(dto.getPhoneNumber());
        u.setGender(dto.getGender());
        u.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getStatus() != null) u.setStatus(User.Status.valueOf(dto.getStatus()));
        u.setPictureUrl(dto.getPictureUrl());
        u.setLocale(dto.getLocale());
        u.setOauthProvider(dto.getOauthProvider());
        return u;
    }
}
