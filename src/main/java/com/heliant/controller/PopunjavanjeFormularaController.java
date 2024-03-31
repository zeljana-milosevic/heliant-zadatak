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

import com.heliant.model.FormularPopunjen;
import com.heliant.service.FormularService;

@RestController
public class PopunjavanjeFormularaController {
	
	@Autowired
	private FormularService formularService;
	
	@GetMapping("/prikaziSvePopunjeneFormulare")
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare() {
		
	    return formularService.prikaziSvePopunjeneFormulare();
	}
	
	@GetMapping("/vratiPopunjenFormularSaId/{formularPopunjenId}")
	public FormularPopunjen vratiFormularSaId(@PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
	    return formularService.vratiFormularPopunjenSaId(formularPopunjenId);
	}
	
	@PostMapping("/sacuvajPopunjenFormular")
	public FormularPopunjen sacuvajPopunjenFormular(@RequestBody FormularPopunjen formularPopunjen) {
		
		return formularService.sacuvajFormularPopunjen(formularPopunjen);
	}
	
	@PutMapping("/izmeniPopunjenFormular")
	public FormularPopunjen izmeniPopunjenFormular(@RequestBody FormularPopunjen formularPopunjen) {
		
		return formularService.izmeniFormularPopunjen(formularPopunjen);
	}
	
	@DeleteMapping("/obrisiPopunjenFormular/{formularPopunjenId}")
	public String obrisiPopunjenFormular(@PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
		formularService.obrisiFormularPopunjenSaId(formularPopunjenId);
		
		return "Popunjen formular uspesno obrisan!";
	}

}
