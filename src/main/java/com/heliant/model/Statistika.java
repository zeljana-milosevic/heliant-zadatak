package com.heliant.model;

import java.time.LocalDate;

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
@Entity @Table(name = "statistika")
public class Statistika extends AbstractEntity {
	
	@NotNull
	@Column(name = "datum")
	private LocalDate datum;
	
	@NotNull
	@Column(name = "broj_popunjenih_formulara")
	private int brojPopunjenihFormulara;

}
