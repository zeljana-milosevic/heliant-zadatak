package com.heliant.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
public class AbstractConfigurationEntity extends AbstractEntity{
	
	@NotNull
	@CreationTimestamp
	@Column(name = "vreme_kreiranja", updatable = false)
	private LocalDateTime vremeKreiranja;
	
	@UpdateTimestamp
	@Column(name = "vreme_poslednje_izmene")
	private LocalDateTime vremePoslednjeIzmene;
	
}
