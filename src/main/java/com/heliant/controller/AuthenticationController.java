package com.heliant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heliant.model.AuthenticationResponse;
import com.heliant.model.Korisnik;
import com.heliant.service.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
public class AuthenticationController {

	@Autowired
    private AuthenticationService authService;
	
	@Operation(summary = "Logovanje korisnika",
		       description = "Logovanje korisnika na osnovu korisnickog imena i lozinke. Odgovor je u formi tokena "
		       		+ "koji se nakon toga koristi za autentifikaciju u ostalim zahtevima.")
	@Tag(name = "post", description = "POST metode Heliant API")
	@PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Korisnik korisnik) {
        return ResponseEntity.ok(authService.authenticate(korisnik));
    }
    
}
