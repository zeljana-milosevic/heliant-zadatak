package com.heliant.service;

import java.security.Principal;
import java.util.List;

import com.heliant.model.Polje;

public interface PoljeService {
	
	public List<Polje> prikaziSvaPolja();
	
	public Polje sacuvajPolje(Polje polje, Principal principal);
	
	public Polje izmeniPolje(Polje polje, Principal principal);
	
	public Polje vratiPoljeSaId(Long id);
	
	public void obrisiPoljeSaId(Long id);
	
	public List<Polje> prikaziSvaPoljaFormulara(Long id);

}
