package com.heliant.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.heliant.model.Formular;
import com.heliant.service.FormularService;

import jakarta.validation.Valid;

@RestController
public class FormularController {
	
	@Autowired
	private FormularService formularService;
	
	@GetMapping("/svi/prikaziSveFormulare")
	public List<Formular> prikaziSveFormulare() {
		
	    return formularService.prikaziSveFormulare();
	}
	
	@GetMapping("/svi/vratiFormularSaId/{formularId}")
	public Formular vratiFormularSaId(@PathVariable("formularId") Long formularId) {
		
	    return formularService.vratiFormularSaId(formularId);
	}
	
	@PostMapping("/admin/sacuvajFormular")
	public Formular sacuvajFormular(@Valid @RequestBody Formular formular, Principal principal) {
		
		return formularService.sacuvajFormular(formular, principal);
	}
	
	@PutMapping("/admin/izmeniFormular")
	public Formular izmeniFormular(@Valid @RequestBody Formular formular, Principal principal) {
		
		return formularService.izmeniFormular(formular, principal);
	}
	
	@DeleteMapping("/admin/obrisiFormular/{formularId}")
	public String izmeniFormular(@PathVariable("formularId") Long formularId) {
		
		formularService.obrisiFormularSaId(formularId);
		
		return "Formular uspesno obrisan!";
	}
	
	@GetMapping("/svi/prikaziFormulare/stranica/{brojStranice}")
	public List<Formular> prikaziFormulareSaPaginacijom(@PathVariable (value = "brojStranice") int brojStranice) {
		
		int brojFormularaPoStranici = 5;
		
		Page<Formular> page = formularService.pronadjiFormulareSaPaginacijom(brojStranice, brojFormularaPoStranici);
		List<Formular> listaFormulara = page.getContent();
		
		return listaFormulara;
	}

}
