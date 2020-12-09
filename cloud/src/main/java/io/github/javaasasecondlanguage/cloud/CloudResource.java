package io.github.javaasasecondlanguage.cloud;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Broken implementation of billing service
 * Money are lost here
 */
@Controller
public class CloudResource {
    /**
     * curl localhost:8080/status
     */
    @RequestMapping(
            path = "status",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> status() {
        return ResponseEntity.ok("It's working!");
    }
}
