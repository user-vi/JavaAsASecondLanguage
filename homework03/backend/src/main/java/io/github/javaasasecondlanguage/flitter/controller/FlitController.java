package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.services.FlitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FlitController {

    @Autowired
    private FlitService flitService;

    @PostMapping("/flit/add")
    void addFlit(@RequestBody String userToken, String content) {
        flitService.add(userToken, content);
    }

    @GetMapping("/flit/discover")
    Map<String, String> discoverFlit() {
        return flitService.discoverFlits();
    }

//    @GetMapping("/flit/list/{username}")
//    String userFlitList(@PathVariable String username) {
//
//    }

//    @GetMapping("/flit/list/feed/{usertoken}")
//    String userFlitListFeed(@PathVariable String usertoken) {
//        return flitService.get(usertoken);
//    }
}
