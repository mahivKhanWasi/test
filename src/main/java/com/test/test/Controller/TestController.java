package com.test.test.Controller;


import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
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

    @GetMapping("")
    public ResponseEntity<Resource> showImage() {
        try {
            // Load the image from the static folder
            Resource image = (Resource) new ClassPathResource("static/images/my-image.jpg");
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "image/jpg")
                    .body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
