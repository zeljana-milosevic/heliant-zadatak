package com.heliant.service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.heliant.model.Formular;
import com.heliant.model.FormularPopunjen;
import com.heliant.model.Korisnik;
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
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	public List<Formular> prikaziSveFormulare() {
		return formularRepository.findAll();
	}

	@Override
	public Formular sacuvajFormular(Formular formular, Principal principal) {
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		formular.setKorisnikKreirao(korisnik);
		formular.setKorisnikPoslednjiAzurirao(korisnik);
		
		return formularRepository.save(formular);
	}
	
	@Override
	public Formular izmeniFormular(Formular formular, Principal principal) {
		
		formular.setVremeKreiranja(vratiFormularSaId(formular.getId()) != null ? vratiFormularSaId(formular.getId()).getVremeKreiranja() : null);
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		formular.setKorisnikKreirao(vratiFormularSaId(formular.getId()) != null ? 
				(vratiFormularSaId(formular.getId()).getKorisnikKreirao() == null ? korisnik : 
					vratiFormularSaId(formular.getId()).getKorisnikKreirao()) : null);
		formular.setKorisnikPoslednjiAzurirao(korisnik);
		
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
	public FormularPopunjen sacuvajFormularPopunjen(FormularPopunjen formularPopunjen, Principal principal) {
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		formularPopunjen.setKorisnikKreirao(korisnik);
		formularPopunjen.setKorisnikPoslednjiAzurirao(korisnik);
		
		for (PoljePopunjeno popunjenoPolje : formularPopunjen.getPopunjenaPoljaFormulara()) {
			popunjenoPolje.setKorisnikKreirao(korisnik);
			popunjenoPolje.setKorisnikPoslednjiAzurirao(korisnik);
		}
		
		return formularPopunjenRepository.save(formularPopunjen);
	}
	
	@Override
	public FormularPopunjen izmeniFormularPopunjen(FormularPopunjen formularPopunjen, Principal principal) {
		
		formularPopunjen.setVremeKreiranja(vratiFormularPopunjenSaId(formularPopunjen.getId()) != null ? 
				vratiFormularPopunjenSaId(formularPopunjen.getId()).getVremeKreiranja() : null);
		
		Set<PoljePopunjeno> staraPolja = vratiFormularPopunjenSaId(formularPopunjen.getId()).getPopunjenaPoljaFormulara();
		
		if (!formularPopunjen.getPopunjenaPoljaFormulara().isEmpty()) {
			for (PoljePopunjeno staroPolje : staraPolja) {
				for (PoljePopunjeno novoPolje : formularPopunjen.getPopunjenaPoljaFormulara()) {
					
					Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
					
					if ((staroPolje.getPolje().getId().equals(novoPolje.getPolje().getId()) && staroPolje.getVrednostTekst() != null && !staroPolje.getVrednostTekst().equals(novoPolje.getVrednostTekst())) ||
							(staroPolje.getPolje().getId().equals(novoPolje.getPolje().getId()) && staroPolje.getVrednostBroj() != null && !staroPolje.getVrednostBroj().equals(novoPolje.getVrednostBroj()))) {
						staroPolje.setKorisnikKreirao(staroPolje.getKorisnikKreirao() == null ? korisnik : staroPolje.getKorisnikKreirao());
						staroPolje.setKorisnikPoslednjiAzurirao(korisnik);
					}
					
					if (staroPolje.getPolje().getId().equals(novoPolje.getPolje().getId())) {
						staroPolje.setVrednostTekst(novoPolje.getVrednostTekst());
						staroPolje.setVrednostBroj(novoPolje.getVrednostBroj());
					}
				}
			}
		}
		
		formularPopunjen.setPopunjenaPoljaFormulara(staraPolja);
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		formularPopunjen.setKorisnikKreirao(vratiFormularPopunjenSaId(formularPopunjen.getId()) != null ? 
				(vratiFormularPopunjenSaId(formularPopunjen.getId()).getKorisnikKreirao() == null ? korisnik : 
					vratiFormularPopunjenSaId(formularPopunjen.getId()).getKorisnikKreirao()) : null);
		formularPopunjen.setKorisnikPoslednjiAzurirao(korisnik);
		
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
	
	@Override
	public List<FormularPopunjen> vratiSvePopunjeneFormulareDanas(LocalDateTime pocetnoVreme, LocalDateTime krajnjeVreme) {
		return formularPopunjenRepository.findByVremeKreiranjaBetween(pocetnoVreme, krajnjeVreme);
	}

	@Override
	public Page<Formular> pronadjiFormulareSaPaginacijom(int brojStranice, int brojFormularaPoStranici) {
		Pageable pageable = PageRequest.of(brojStranice - 1, brojFormularaPoStranici);
		return formularRepository.findAll(pageable);
	}
	
}
