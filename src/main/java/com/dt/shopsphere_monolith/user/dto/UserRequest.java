package com.dt.shopsphere_monolith.user.dto;

public record UserRequest (
        String firstName,
        String lastName,
        String email,
        String password
){}
