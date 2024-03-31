package com.heliant.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "formular", uniqueConstraints = @UniqueConstraint(columnNames={"NAZIV"}))
public class Formular extends AbstractConfigurationEntity {
	
	@NotNull
	@Column(name = "naziv")
	private String naziv;

	@Getter(onMethod = @__( @JsonManagedReference))
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "formular", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
	private Set<Polje> poljaFormulara = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_kreirao")
	private Korisnik korisnikKreirao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_poslednji_azurirao")
	private Korisnik korisnikPoslednjiAzurirao;
}
