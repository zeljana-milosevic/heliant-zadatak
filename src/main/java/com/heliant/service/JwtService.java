package com.heliant.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.heliant.model.Korisnik;
import com.heliant.repository.TokenRepository;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "6eff9ca37e1744244ea07cfe4afc0a89fe2cd0b83210ea16aa763b48a3b12ba4";
    
    @Autowired
    private TokenRepository tokenRepository;

    public String vratiKorisnickoIme(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails userDetails) {
        String username = vratiKorisnickoIme(token);

        boolean validanToken = tokenRepository.findByToken(token).map(t -> !t.isIzlogovan()).orElse(false);

        return (username.equals(userDetails.getUsername())) && !daLiJeTokenIstekao(token) && validanToken;
    }

    private boolean daLiJeTokenIstekao(String token) {
        return vratiDatumIsteka(token).before(new Date());
    }

    private Date vratiDatumIsteka(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(vratiSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generisiToken(Korisnik korisnik) {
        String token = Jwts
                .builder()
                .subject(korisnik.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 24*60*60*1000))
                .signWith(vratiSigninKey())
                .compact();

        return token;
    }

    private SecretKey vratiSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
