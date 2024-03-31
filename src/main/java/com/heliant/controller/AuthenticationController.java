package com.heliant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heliant.model.AuthenticationResponse;
import com.heliant.model.Korisnik;
import com.heliant.service.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
    private AuthenticationService authService;
	
	@PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Korisnik korisnik) {
        return ResponseEntity.ok(authService.authenticate(korisnik));
    }
    
}
