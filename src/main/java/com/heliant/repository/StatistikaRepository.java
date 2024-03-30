package com.heliant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliant.model.Statistika;

@Repository
public interface StatistikaRepository extends JpaRepository<Statistika, Long> {
	
}
