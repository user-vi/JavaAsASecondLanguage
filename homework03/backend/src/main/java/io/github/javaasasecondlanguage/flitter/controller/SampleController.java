package io.github.javaasasecondlanguage.flitter.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.javaasasecondlanguage.flitter.user.Data;
import io.github.javaasasecondlanguage.flitter.user.User;
import io.github.javaasasecondlanguage.flitter.user.UserName;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class SampleController {
    private Map<String, User> users = new HashMap<>();
    private Map<String, String> flits = new HashMap<>();
    private Map<String, String> subscribes = new HashMap<>();

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
        User user = new User(userName);
        users.put(user.getUsername(), user);
        return new Data(user);
    }

    @GetMapping("/user/list")
    @JsonProperty("data")
    Set<String> geListOfUsers() {
        return users.keySet();
    }

    @PostMapping("/flit/add")
    void addFlit(@RequestBody String userToken, String content) {
        flits.put(userToken, content);
    }

    @GetMapping("/flit/discover")
    Map<String, String> discoverFlit(@RequestBody String userToken, String content) {
        Map<String, String> response =  new HashMap<>();
        for(int i = 0; i < 10; i++) {
            Integer temp = flits.size() - i;
        }
    }

    @GetMapping("/flit/list/{username}")
    String userFlitList(@PathVariable String username) {
        return flits.get(username);
    }

    @GetMapping("/flit/list/feed/{usertoken}")
    String userFlitListFeed(@PathVariable String usertoken) {
        return flits.get(usertoken);
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

