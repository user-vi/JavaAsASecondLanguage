package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SampleController {

    @Autowired
    private UserService userService;

    private Map<String, String> subscribes = new HashMap<>();

//    @GetMapping("/greeting")
//    String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
//        System.out.println("Invoked greeting: " + name);
//        return "Hello " + name;
//    }

    @DeleteMapping("/clear")
    void delete() {
        userService.clear();
    }

    @PostMapping("/subscribe")
    void subscribe(@RequestBody String subscriberToken, String publisherName) {
        subscribes.put(subscriberToken, publisherName);
    }

    @PostMapping("/unsubscribe")
    void unsubscribe(@RequestBody String subscriberToken, String publisherName) {
        subscribes.remove(subscriberToken, publisherName);
    }

    @GetMapping("/subscribers/list/{userToken}")
    Set<String> userSubscribersList(@PathVariable String userToken) {

        Set<String> subscribers = Collections.<String>emptySet();
        for (Map.Entry<String, String> entry : subscribes.entrySet()) {
            if (Objects.equals(userToken, entry.getValue())) {
                subscribers.add(entry.getKey());
            }
        }
        return subscribers;
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


}

