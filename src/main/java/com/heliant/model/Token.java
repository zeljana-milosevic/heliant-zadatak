package com.heliant.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity @Table(name = "token")
public class Token extends AbstractEntity {

    @Column(name = "token")
    private String token;

    @Column(name = "izlogovan")
    private boolean izlogovan;

    @Getter(onMethod = @__( @JsonBackReference))
    @ManyToOne
    @JoinColumn(name = "id_korisnik")
    private Korisnik korisnik;

}
