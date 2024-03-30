package com.heliant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heliant.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
}
