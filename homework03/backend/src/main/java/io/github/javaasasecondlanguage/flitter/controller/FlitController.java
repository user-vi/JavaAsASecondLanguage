package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.flits.AddFLitRequest;
import io.github.javaasasecondlanguage.flitter.flits.FlitRegistrationResult;
import io.github.javaasasecondlanguage.flitter.flits.FlitService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlitController {

    @Autowired
    private UserService userService;

    @Autowired
    private FlitService flitService;

    @PostMapping("/flit/add")
    ResponseEntity<?> addFlit(@RequestBody AddFLitRequest addFLitRequest) {
        FlitRegistrationResult addingResult = flitService.add(addFLitRequest.getUserToken(), addFLitRequest.getContent());
        if (addingResult == FlitRegistrationResult.SUCCESS)
            return new ResponseEntity<>(new Result("success", null), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Result("fail", "Some error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/flit/discover")
    Result discoverFlit() {
        return flitService.discoverFlits();
    }

    @GetMapping("/flit/list/{username}")
    ResponseEntity<?> userFlitList(@PathVariable("username") String username) {
        if (userService.isUserNameRegistered(username))
            return new ResponseEntity<>(
                    new Result(flitService.flintsOfUser(username), null), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Result(null, "User does not registered"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/flit/list/feed/{usertoken}")
    ResponseEntity<?> feeds(@PathVariable("usertoken") String userToken) {
        if (userService.isUserTokenRegistered(userToken))
            return new ResponseEntity<>(flitService.getUserFeed(userToken), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Result(null, "User not found"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
