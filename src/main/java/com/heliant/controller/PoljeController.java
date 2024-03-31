package com.heliant.controller;

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
	
	@GetMapping("/prikaziSvaPolja")
	public List<Polje> prikaziSvaPolja() {
		
	    return poljeService.prikaziSvaPolja();
	}
	
	@GetMapping("/prikaziSvaPoljaFormulara/{formularId}")
	public List<Polje> prikaziSvaPolja(@PathVariable("formularId") Long formularId) {
		
	    return poljeService.prikaziSvaPoljaFormulara(formularId);
	}
	
	@PostMapping("/sacuvajPolje")
	public Polje sacuvajPolje(@RequestBody Polje polje) {
		
		return poljeService.sacuvajPolje(polje);
	}
	
	@PutMapping("/izmeniPolje")
	public Polje izmeniPolje(@RequestBody Polje polje) {
		
		return poljeService.izmeniPolje(polje);
	}
	
	@DeleteMapping("/obrisiPolje/{poljeId}")
	public String obrisiPolje(@PathVariable("poljeId") Long poljeId) {
		
		poljeService.obrisiPoljeSaId(poljeId);
		
		return "Polje uspesno obrisano!";
	}

}
