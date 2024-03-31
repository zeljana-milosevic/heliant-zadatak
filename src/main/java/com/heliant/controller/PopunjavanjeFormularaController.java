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

import com.heliant.model.FormularPopunjen;
import com.heliant.service.FormularService;

import jakarta.validation.Valid;

@RestController
public class PopunjavanjeFormularaController {
	
	@Autowired
	private FormularService formularService;
	
	@GetMapping("/svi/prikaziSvePopunjeneFormulare")
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare() {
		
	    return formularService.prikaziSvePopunjeneFormulare();
	}
	
	@GetMapping("/svi/vratiPopunjenFormularSaId/{formularPopunjenId}")
	public FormularPopunjen vratiFormularSaId(@PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
	    return formularService.vratiFormularPopunjenSaId(formularPopunjenId);
	}
	
	@PostMapping("/svi/sacuvajPopunjenFormular")
	public FormularPopunjen sacuvajPopunjenFormular(@Valid @RequestBody FormularPopunjen formularPopunjen, Principal principal) {
		
		return formularService.sacuvajFormularPopunjen(formularPopunjen, principal);
	}
	
	@PutMapping("/svi/izmeniPopunjenFormular")
	public FormularPopunjen izmeniPopunjenFormular(@Valid @RequestBody FormularPopunjen formularPopunjen, Principal principal) {
		
		return formularService.izmeniFormularPopunjen(formularPopunjen, principal);
	}
	
	@DeleteMapping("/svi/obrisiPopunjenFormular/{formularPopunjenId}")
	public String obrisiPopunjenFormular(@PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
		formularService.obrisiFormularPopunjenSaId(formularPopunjenId);
		
		return "Popunjen formular uspesno obrisan!";
	}

}
