package com.heliant.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "polje_popunjeno")
public class PoljePopunjeno extends AbstractConfigurationEntity implements Comparable<PoljePopunjeno> {

	@Getter(onMethod = @__( @JsonBackReference))
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_formular_popunjen")
	private FormularPopunjen formularPopunjen;
	
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_polje")
	private Polje polje;
	
	@Column(name = "vrednost_tekst")
	private String vrednostTekst;
	
	@Column(name = "vrednost_broj")
	private Double vrednostBroj;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_kreirao")
	private Korisnik korisnikKreirao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_poslednji_azurirao")
	private Korisnik korisnikPoslednjiAzurirao;
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getPolje().getId());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PoljePopunjeno other = (PoljePopunjeno) obj;
		return Objects.equals(this.getPolje().getId(), other.getPolje().getId());
	}

	@Override
	public int compareTo(PoljePopunjeno o) {
		return this.getPolje().getPrikazniRedosled().compareTo(o.getPolje().getPrikazniRedosled());
	}
	
}
