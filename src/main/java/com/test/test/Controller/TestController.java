package com.test.test.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TestController {

    @GetMapping("/murgichor")
    public ResponseEntity<?> getMurgiChor() {
        return ResponseEntity.status(HttpStatus.OK).body("Alamin is the murgi chor.");
    }
}
