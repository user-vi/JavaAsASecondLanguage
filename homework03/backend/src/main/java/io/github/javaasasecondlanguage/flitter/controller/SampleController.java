package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.user.Data;
import io.github.javaasasecondlanguage.flitter.user.User;
import io.github.javaasasecondlanguage.flitter.user.UserName;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SampleController {
    private Map<String, User> users = new HashMap<>();

    @GetMapping("/greeting")
    String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        System.out.println("Invoked greeting: " + name);
        return "Hello " + name;
    }


    @DeleteMapping("/clear")
    void delete() {
        users.clear();
    }

    @PostMapping("/user/register")
    Data register(@RequestBody UserName userName) {
        User user = new User(userName.getUserName());
        users.put(user.getUsername(), user);
        return new Data(user);
    }

//    "/user/list": returns the list of names of all registered users.
//            Method: GET
//    Accepts: nothing
//    Returns: list of user {"data":[<user name 1>,<user name 2>]}

    @GetMapping("/user/list")
    void geListOfUsers(@RequestParam() String name) {
        return;
    }


}
