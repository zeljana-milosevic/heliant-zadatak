package com.heliant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.heliant.model.AuthenticationResponse;
import com.heliant.model.Korisnik;
import com.heliant.model.Token;
import com.heliant.repository.KorisnikRepository;
import com.heliant.repository.TokenRepository;

import java.util.List;

@Service
public class AuthenticationService {

	@Autowired
    private KorisnikRepository korisnikRepository;
	
	@Autowired
    private JwtService jwtService;

	@Autowired
    private TokenRepository tokenRepository;

	@Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse authenticate(Korisnik request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Korisnik korisnik = korisnikRepository.findByKorisnickoIme(request.getUsername()).orElseThrow();
        String jwt = jwtService.generisiToken(korisnik);

        opozvatiSveTokeneZaKorisnika(korisnik);
        sacuvajTokenKorisnika(jwt, korisnik);

        return new AuthenticationResponse(jwt, "Korisnik uspesno ulogovan");

    }
    private void opozvatiSveTokeneZaKorisnika(Korisnik korisnik) {
        List<Token> validniTokeni = tokenRepository.pronadjiSveTokeneZaKorisnika(korisnik.getId());
        
        if (validniTokeni.isEmpty()) {
            return;
        }

        validniTokeni.forEach(t-> {
            t.setIzlogovan(true);
        });

        tokenRepository.saveAll(validniTokeni);
    }
    
    private void sacuvajTokenKorisnika(String jwt, Korisnik korisnik) {
        Token token = new Token();
        token.setToken(jwt);
        token.setIzlogovan(false);
        token.setKorisnik(korisnik);
        tokenRepository.save(token);
    }
}
