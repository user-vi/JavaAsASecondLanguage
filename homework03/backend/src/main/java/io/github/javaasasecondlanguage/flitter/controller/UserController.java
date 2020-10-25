package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.services.UserAlreadyExistsException;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import io.github.javaasasecondlanguage.flitter.user.DataUserList;
import io.github.javaasasecondlanguage.flitter.user.UserName;
import io.github.javaasasecondlanguage.flitter.user.UserRegistrationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    ResponseEntity<?> register(@RequestBody UserName userName) {
        UserRegistrationResult userRegistrationEvent = userService.register(userName.getUserName());
        if(userRegistrationEvent.getErrorMessage() == null)
            return new ResponseEntity<>(userRegistrationEvent, HttpStatus.OK);
        else
            return new ResponseEntity<>(userRegistrationEvent, HttpStatus.INTERNAL_SERVER_ERROR);
//        return userService.register(userName.getUserName());
    }

    @GetMapping("/user/list")
    DataUserList geListOfUsers() {
        return new DataUserList(userService.list());
    }
}
