package com.heliant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heliant.model.Token;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("select t from Token t inner join Korisnik k on t.korisnik.id = k.id "
    		+ "where t.korisnik.id = :korisnikId and t.izlogovan = false")
    List<Token> pronadjiSveTokeneZaKorisnika(Long korisnikId);

    Optional<Token> findByToken(String token);
}