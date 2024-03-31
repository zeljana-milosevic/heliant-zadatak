package com.heliant.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heliant.model.Formular;
import com.heliant.model.FormularPopunjen;
import com.heliant.model.Polje;
import com.heliant.model.PoljePopunjeno;
import com.heliant.repository.FormularPopunjenRepository;
import com.heliant.repository.FormularRepository;

@Service
public class FormularServiceImpl implements FormularService {

	@Autowired
	private FormularRepository formularRepository;
	
	@Autowired
	private FormularPopunjenRepository formularPopunjenRepository;
	
	@Override
	public List<Formular> prikaziSveFormulare() {
		return formularRepository.findAll();
	}

	@Override
	public Formular sacuvajFormular(Formular formular) {
		
		return formularRepository.save(formular);
	}
	
	@Override
	public Formular izmeniFormular(Formular formular) {
		
		formular.setVremeKreiranja(vratiFormularSaId(formular.getId()).getVremeKreiranja());
		
		return formularRepository.save(formular);
	}

	@Override
	public Formular vratiFormularSaId(Long id) {
		Optional<Formular> formular = formularRepository.findById(id);
		
		if (formular.isPresent()) {
			formular.get().setPoljaFormulara(new TreeSet<Polje>(formular.get().getPoljaFormulara()));
			return formular.get();
		} else {
			return null;
		}
	}

	@Override
	public void obrisiFormularSaId(Long id) {
		formularRepository.deleteById(id);
	}

	@Override
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare() {
		return formularPopunjenRepository.findAll();
	}

	@Override
	public FormularPopunjen sacuvajFormularPopunjen(FormularPopunjen formularPopunjen) {
		
		return formularPopunjenRepository.save(formularPopunjen);
	}
	
	@Override
	public FormularPopunjen izmeniFormularPopunjen(FormularPopunjen formularPopunjen) {
		
		formularPopunjen.setVremeKreiranja(vratiFormularPopunjenSaId(formularPopunjen.getId()).getVremeKreiranja());
		
		Set<PoljePopunjeno> staraPolja = vratiFormularPopunjenSaId(formularPopunjen.getId()).getPopunjenaPoljaFormulara();
		
		if (!formularPopunjen.getPopunjenaPoljaFormulara().isEmpty()) {
			for (PoljePopunjeno staroPolje : staraPolja) {
				for (PoljePopunjeno novoPolje : formularPopunjen.getPopunjenaPoljaFormulara()) {
					if (staroPolje.getPolje().getId().equals(novoPolje.getPolje().getId())) {
						staroPolje.setVrednostTekst(novoPolje.getVrednostTekst());
						staroPolje.setVrednostBroj(novoPolje.getVrednostBroj());
					}
				}
			}
		}
		
		formularPopunjen.setPopunjenaPoljaFormulara(staraPolja);
		
		return formularPopunjenRepository.save(formularPopunjen);
	}

	@Override
	public FormularPopunjen vratiFormularPopunjenSaId(Long id) {
		Optional<FormularPopunjen> formularPopunjen = formularPopunjenRepository.findById(id);
		
		if (formularPopunjen.isPresent()) {
			formularPopunjen.get().setPopunjenaPoljaFormulara(new TreeSet<PoljePopunjeno>(formularPopunjen.get().getPopunjenaPoljaFormulara()));
			formularPopunjen.get().setFormular(vratiFormularSaId(formularPopunjen.get().getFormular().getId()));
			return formularPopunjen.get();
		} else {
			return null;
		}
	}

	@Override
	public void obrisiFormularPopunjenSaId(Long id) {
		formularPopunjenRepository.deleteById(id);
	}
	
}
