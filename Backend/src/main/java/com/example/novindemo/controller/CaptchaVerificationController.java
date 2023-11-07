package com.example.novindemo.controller;

import com.example.novindemo.dto.RecaptchaResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/captcha")
public class CaptchaVerificationController {

    @Value("${recaptcha.secret-key}")
    private String recaptchaSecretKey;
    @PostMapping("/verify-captcha")
    public ResponseEntity<String> verifyCaptcha(@RequestParam("recaptchaResponse") String recaptchaResponse) {
        String verificationUrl = "https://www.google.com/recaptcha/api/siteverify?secret=" + recaptchaSecretKey
                + "&response=" + recaptchaResponse;

        RestTemplate restTemplate = new RestTemplate();
        RecaptchaResponse response = restTemplate.postForObject(verificationUrl, null, RecaptchaResponse.class);

        assert response != null;
        if (response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.OK).body("Success");
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failure");
        }
    }
}
