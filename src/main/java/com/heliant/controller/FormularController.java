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

import com.heliant.model.Formular;
import com.heliant.service.FormularService;

@RestController
public class FormularController {
	
	@Autowired
	private FormularService formularService;
	
	@GetMapping("/prikaziSveFormulare")
	public List<Formular> prikaziSveFormulare() {
		
	    return formularService.prikaziSveFormulare();
	}
	
	@GetMapping("/vratiFormularSaId/{formularId}")
	public Formular vratiFormularSaId(@PathVariable("formularId") Long formularId) {
		
	    return formularService.vratiFormularSaId(formularId);
	}
	
	@PostMapping("/sacuvajFormular")
	public Formular sacuvajFormular(@RequestBody Formular formular) {
		
		return formularService.sacuvajFormular(formular);
	}
	
	@PutMapping("/izmeniFormular")
	public Formular izmeniFormular(@RequestBody Formular formular) {
		
		return formularService.izmeniFormular(formular);
	}
	
	@DeleteMapping("/obrisiFormular/{formularId}")
	public String izmeniFormular(@PathVariable("formularId") Long formularId) {
		
		formularService.obrisiFormularSaId(formularId);
		
		return "Formular uspesno obrisan!";
	}

}
