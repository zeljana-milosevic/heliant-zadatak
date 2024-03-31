package com.heliant.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import com.heliant.model.Formular;
import com.heliant.model.FormularPopunjen;

public interface FormularService {
	
	public List<Formular> prikaziSveFormulare();
	
	public Formular sacuvajFormular(Formular formular, Principal principal);
	
	public Formular izmeniFormular(Formular formular, Principal principal);
	
	public Formular vratiFormularSaId(Long id);
	
	public void obrisiFormularSaId(Long id);
	
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare();
	
	public FormularPopunjen sacuvajFormularPopunjen(FormularPopunjen formularPopunjen, Principal principal);
	
	public FormularPopunjen izmeniFormularPopunjen(FormularPopunjen formularPopunjen, Principal principal);
	
	public FormularPopunjen vratiFormularPopunjenSaId(Long id);
	
	public void obrisiFormularPopunjenSaId(Long id);
	
	List<FormularPopunjen> vratiSvePopunjeneFormulareDanas(LocalDateTime pocetnoVreme, LocalDateTime krajnjeVreme);
	
	Page<Formular> pronadjiFormulareSaPaginacijom(int brojStranice, int brojFormularaPoStranici);
	
}
