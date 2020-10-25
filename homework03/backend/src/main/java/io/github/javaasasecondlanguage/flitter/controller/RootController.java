package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.flits.FlitService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
import io.github.javaasasecondlanguage.flitter.subscribers.SubscribeRequest;
import io.github.javaasasecondlanguage.flitter.subscribers.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class RootController {

    @Autowired
    private UserService userService;

    @Autowired
    private FlitService flitService;

    @Autowired
    private SubscribeService subscribeService;

    private Map<String, String> subscribes = new HashMap<>();


    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Result result = subscribeService.subscribe(subscribeRequest.getSubscriberToken(), subscribeRequest.getPublisherName());
        if (result.getErrorMessage() == null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/unsubscribe")
    ResponseEntity<?> unsubscribe(@RequestBody SubscribeRequest subscribeRequest) {
        Result result = subscribeService.unsubscribe(subscribeRequest.getSubscriberToken(), subscribeRequest.getPublisherName());
        if (result.getErrorMessage() == null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/subscribers/list/{userToken}")
    ResponseEntity<?> userSubscribersList(@PathVariable("userToken") String userToken) {
        Result result = subscribeService.subscribers(userToken);
        if (result.getErrorMessage() == null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/publishers/list/{userToken}")
    ResponseEntity<?> userTokenPublishersList(@PathVariable String userToken) {
        if (userService.isUserTokenRegistered(userToken)) {
            return new ResponseEntity<>(new Result(subscribeService.publisersNames(userToken), null), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new Result(null,"User not found" ), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @DeleteMapping("/clear")
    void delete() {
        userService.clear();
        flitService.clear();
        subscribeService.clear();
    }
}

