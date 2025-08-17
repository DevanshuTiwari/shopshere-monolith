package com.dt.shopsphere_monolith.user.service;

import com.dt.shopsphere_monolith.user.dto.UserRequest;

public interface UserService {
    void registerUser(UserRequest userRequest);
}
