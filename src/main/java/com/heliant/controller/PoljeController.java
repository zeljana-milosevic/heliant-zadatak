package com.heliant.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heliant.model.Polje;
import com.heliant.service.PoljeService;

@RestController
public class PoljeController {
	
	@Autowired
	private PoljeService poljeService;
	
	@GetMapping("/svi/prikaziSvaPolja")
	public List<Polje> prikaziSvaPolja() {
		
	    return poljeService.prikaziSvaPolja();
	}
	
	@GetMapping("/svi/prikaziSvaPoljaFormulara/{formularId}")
	public List<Polje> prikaziSvaPolja(@PathVariable("formularId") Long formularId) {
		
	    return poljeService.prikaziSvaPoljaFormulara(formularId);
	}
	
	@PostMapping("/admin/sacuvajPolje")
	public Polje sacuvajPolje(@RequestBody Polje polje, Principal principal) {
		
		return poljeService.sacuvajPolje(polje, principal);
	}
	
	@PutMapping("/admin/izmeniPolje")
	public Polje izmeniPolje(@RequestBody Polje polje, Principal principal) {
		
		return poljeService.izmeniPolje(polje, principal);
	}
	
	@DeleteMapping("/admin/obrisiPolje/{poljeId}")
	public String obrisiPolje(@PathVariable("poljeId") Long poljeId) {
		
		poljeService.obrisiPoljeSaId(poljeId);
		
		return "Polje uspesno obrisano!";
	}

}
