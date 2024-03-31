package com.heliant.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heliant.model.Korisnik;
import com.heliant.model.Polje;
import com.heliant.repository.PoljeRepository;

@Service
public class PoljeServiceImpl implements PoljeService {

	@Autowired
	private PoljeRepository poljeRepository;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Override
	public List<Polje> prikaziSvaPolja() {
		return poljeRepository.findAll();
	}

	@Override
	public Polje sacuvajPolje(Polje polje, Principal principal) {
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		polje.setKorisnikKreirao(korisnik);
		polje.setKorisnikPoslednjiAzurirao(korisnik);
		
		return poljeRepository.save(polje);
	}
	
	@Override
	public Polje izmeniPolje(Polje polje, Principal principal) {
		
		Korisnik korisnik = (Korisnik) userDetailsServiceImpl.loadUserByUsername(principal.getName());
		
		polje.setKorisnikKreirao(vratiPoljeSaId(polje.getId()) != null ? 
				(vratiPoljeSaId(polje.getId()).getKorisnikKreirao() == null ? korisnik : 
					vratiPoljeSaId(polje.getId()).getKorisnikKreirao()) : null);
		polje.setKorisnikPoslednjiAzurirao(korisnik);
		
		polje.setVremeKreiranja(vratiPoljeSaId(polje.getId()) != null ? vratiPoljeSaId(polje.getId()).getVremeKreiranja() : null);
		
		return poljeRepository.save(polje);
	}

	@Override
	public Polje vratiPoljeSaId(Long id) {
		Optional<Polje> polje = poljeRepository.findById(id);
		return polje.isPresent() ? polje.get() : null ;
	}

	@Override
	public void obrisiPoljeSaId(Long id) {
		poljeRepository.deleteById(id);
	}

	@Override
	public List<Polje> prikaziSvaPoljaFormulara(Long id) {
		return poljeRepository.findAllByFormularId(id);
	}
	
}
