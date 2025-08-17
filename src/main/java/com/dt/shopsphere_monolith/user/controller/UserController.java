package com.dt.shopsphere_monolith.user.controller;


import com.dt.shopsphere_monolith.user.dto.UserRequest;
import com.dt.shopsphere_monolith.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser(@RequestBody UserRequest userRequest) {
        userService.registerUser(userRequest);
    }

}
