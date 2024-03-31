package com.heliant.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heliant.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);
	
}
