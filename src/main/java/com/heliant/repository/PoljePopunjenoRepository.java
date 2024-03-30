package com.heliant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heliant.model.PoljePopunjeno;

@Repository
public interface PoljePopunjenoRepository extends JpaRepository<PoljePopunjeno, Long> {

}
