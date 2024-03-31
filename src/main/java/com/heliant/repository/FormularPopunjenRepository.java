package com.heliant.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliant.model.FormularPopunjen;

@Repository
public interface FormularPopunjenRepository extends JpaRepository<FormularPopunjen, Long> {
	
	List<FormularPopunjen> findByVremeKreiranjaBetween(LocalDateTime pocetnoVreme, LocalDateTime krajnjeVreme);
	
}
