package com.heliant.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "formular_popunjen")
public class FormularPopunjen extends AbstractConfigurationEntity {
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_formular")
	private Formular formular;
	
	@Getter(onMethod = @__( @JsonManagedReference))
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "formularPopunjen", cascade = CascadeType.ALL)
	private Set<PoljePopunjeno> popunjenaPoljaFormulara = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_kreirao")
	private Korisnik korisnikKreirao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_poslednji_azurirao")
	private Korisnik korisnikPoslednjiAzurirao;
	
}
