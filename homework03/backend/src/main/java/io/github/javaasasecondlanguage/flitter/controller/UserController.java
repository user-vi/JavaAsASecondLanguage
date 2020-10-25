package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.services.UserService;
import io.github.javaasasecondlanguage.flitter.user.DataRegister;
import io.github.javaasasecondlanguage.flitter.user.DataUserList;
import io.github.javaasasecondlanguage.flitter.user.User;
import io.github.javaasasecondlanguage.flitter.user.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    DataRegister register(@RequestBody UserName userName) {
        User user = userService.register(userName.getUserName());
        return new DataRegister(user);
    }

    @GetMapping("/user/list")
    DataUserList geListOfUsers() {
        return new DataUserList(userService.list());
    }
}
