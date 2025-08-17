package com.dt.shopsphere_monolith.user.dto;

public record UserResponse(
        Long id,
        String firstName,
        String lastName,
        String email
) {}
