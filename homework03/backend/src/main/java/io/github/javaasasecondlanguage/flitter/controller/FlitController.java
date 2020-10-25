package io.github.javaasasecondlanguage.flitter.controller;

import io.github.javaasasecondlanguage.flitter.flits.AddFLitRequest;
import io.github.javaasasecondlanguage.flitter.flits.FlitRegistrationResult;
import io.github.javaasasecondlanguage.flitter.flits.ResultFlit;
import io.github.javaasasecondlanguage.flitter.services.FlitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class FlitController {

    @Autowired
    private FlitService flitService;

    @PostMapping("/flit/add")
    ResponseEntity<?> addFlit(@RequestBody AddFLitRequest addFLitRequest) {
        if(flitService.add(addFLitRequest.getUserToken(), addFLitRequest.getContent()) == FlitRegistrationResult.SUCCESS)
            return new ResponseEntity<>(new Result("success", null), HttpStatus.OK);
        else
            return new ResponseEntity<>(new Result("fail", "Some error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/flit/discover")
    Result discoverFlit() {
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
