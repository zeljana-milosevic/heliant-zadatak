package com.heliant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.heliant.enums.Tip;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity @Table(name = "polje", uniqueConstraints = {@UniqueConstraint(columnNames={"ID_FORMULAR", "PRIKAZNI_REDOSLED"}),
		@UniqueConstraint(columnNames={"ID_FORMULAR", "NAZIV"})})
public class Polje extends AbstractConfigurationEntity implements Comparable<Polje> {

	@Getter(onMethod = @__( @JsonBackReference))
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_formular")
	private Formular formular;
	
	@NotNull
	@Column(name = "naziv")
	private String naziv;
	
	@NotNull
	@Column(name = "prikazni_redosled")
	private Integer prikazniRedosled;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "tip")
	private Tip tip;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_kreirao")
	private Korisnik korisnikKreirao;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_korisnik_poslednji_azurirao")
	private Korisnik korisnikPoslednjiAzurirao;
	
	@Override
	public int compareTo(Polje o) {
		return this.getPrikazniRedosled().compareTo(o.getPrikazniRedosled());
	}
	
}
