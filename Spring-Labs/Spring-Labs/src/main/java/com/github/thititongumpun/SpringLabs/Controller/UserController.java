package com.github.thititongumpun.SpringLabs.Controller;

import com.github.thititongumpun.SpringLabs.Entities.User;
import com.github.thititongumpun.SpringLabs.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.thititongumpun.SpringLabs.Utility.Web.API;

@RequiredArgsConstructor
@RestController
@RequestMapping(API + "/user")
public class UserController {
    private final UserService userService;

//    @GetMapping
//    public List<String> getUsers() {
//        return this.userService.getDummyData();
//    }

    @GetMapping
    public List<User> getUsers() {
        return this.userService.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return this.userService.create(user);
    }
}
