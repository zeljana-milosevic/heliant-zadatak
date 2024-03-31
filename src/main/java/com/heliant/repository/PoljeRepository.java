package com.heliant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliant.model.Polje;

@Repository
public interface PoljeRepository extends JpaRepository<Polje, Long> {
	
	public List<Polje> findAllByFormularId(Long formularId);

}
