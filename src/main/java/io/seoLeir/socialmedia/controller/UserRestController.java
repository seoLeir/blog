package io.seoLeir.socialmedia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {


    @GetMapping("/{username}")
    public ResponseEntity<String> getUserProfile(@PathVariable("username") String username){
        return null;
    }
}
