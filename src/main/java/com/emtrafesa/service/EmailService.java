package com.emtrafesa.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendPasswordResetEmail(String email, String token) {
        System.out.println("Sending password reset email to " + email + " with token " + token);
    }
}