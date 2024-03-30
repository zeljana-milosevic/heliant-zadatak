package com.heliant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "korisnik")
public class Korisnik extends AbstractConfigurationEntity {

	@NotNull
	@Column(name = "korisnicko_ime")
	private String korisnickoIme;
	
	@NotNull
	@Column(name = "lozinka")
	private String lozinka;

}
