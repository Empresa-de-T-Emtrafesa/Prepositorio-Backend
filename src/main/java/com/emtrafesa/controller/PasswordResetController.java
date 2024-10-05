package com.emtrafesa.controller;
import com.emtrafesa.dto.ResetPasswordRequestDTO;
import com.emtrafesa.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password-reset")
public class PasswordResetController {

    private final PasswordResetService passwordResetService;

    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @PostMapping("/generate")
    public ResponseEntity<String> generateResetLink(@RequestBody ResetPasswordRequestDTO request) {
        String response = passwordResetService.generateResetLink(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        String response = passwordResetService.resetPassword(token, newPassword);
        return ResponseEntity.ok(response);
    }
}
