package com.heliant.service;

import java.time.LocalDateTime;
import java.util.List;

import com.heliant.model.Formular;
import com.heliant.model.FormularPopunjen;

public interface FormularService {
	
	public List<Formular> prikaziSveFormulare();
	
	public Formular sacuvajFormular(Formular formular);
	
	public Formular izmeniFormular(Formular formular);
	
	public Formular vratiFormularSaId(Long id);
	
	public void obrisiFormularSaId(Long id);
	
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare();
	
	public FormularPopunjen sacuvajFormularPopunjen(FormularPopunjen formularPopunjen);
	
	public FormularPopunjen izmeniFormularPopunjen(FormularPopunjen formularPopunjen);
	
	public FormularPopunjen vratiFormularPopunjenSaId(Long id);
	
	public void obrisiFormularPopunjenSaId(Long id);
	
	List<FormularPopunjen> vratiSvePopunjeneFormulareDanas(LocalDateTime pocetnoVreme, LocalDateTime krajnjeVreme);
	
}
