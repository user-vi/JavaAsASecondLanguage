package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.flits.FlitService;
import io.github.javaasasecondlanguage.flitter.services.UserService;
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
    public ResponseEntity<?> subscribe(@RequestBody String subscriberToken, String publisherName) {
        Result result = subscribeService.subscribe(subscriberToken, publisherName);
        if (result.getErrorMessage() == null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PostMapping("/unsubscribe")
    void unsubscribe(@RequestBody String subscriberToken, String publisherName) {
        subscribes.remove(subscriberToken, publisherName);
    }

    @GetMapping("/subscribers/list/{userToken}")
    ResponseEntity<?> userSubscribersList(@PathVariable("userToken") String userToken) {
        Result result = subscribeService.list(userToken);
        if (result.getErrorMessage() == null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        else
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/publishers/list/{userToken}")
    Set<String> userTokenPublishersList(@PathVariable String userToken) {

        Set<String> publishers = Collections.<String>emptySet();
        for (Map.Entry<String, String> entry : subscribes.entrySet()) {
            if (Objects.equals(userToken, entry.getValue())) {
                publishers.add(entry.getKey());
            }
        }
        return publishers;
    }



    @DeleteMapping("/clear")
    void delete() {
        userService.clear();
        flitService.clear();
        subscribeService.clear();
    }
}

