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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
public class FormularController {
	
	@Autowired
	private FormularService formularService;
	
	@Operation(summary = "Prikaz svih formulara",
		       description = "Prikazuju se svi formulari sa svojim svojstvima: id, naziv, vreme kreiranja, vreme "
		       		+ "poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/prikaziSveFormulare")
	public List<Formular> prikaziSveFormulare() {
		
	    return formularService.prikaziSveFormulare();
	}
	@Operation(summary = "Prikaz odredjenog formulara",
		       description = "Prikazuje se odredjeni formular sa svojim svojstvima: id, naziv, vreme kreiranja, vreme "
		       		+ "poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/vratiFormularSaId/{formularId}")
	public Formular vratiFormularSaId(@Parameter(
		       description = "ID formulara koji treba da se prikaze",
		       required = true) @PathVariable("formularId") Long formularId) {
		
	    return formularService.vratiFormularSaId(formularId);
	}
	
	@Operation(summary = "Kreiranje formulara",
		       description = "Kreira se formular sa svojim svojstvima: id, naziv, vreme kreiranja, vreme "
		       		+ "poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "post", description = "POST metode Heliant API")
	@PostMapping("/admin/sacuvajFormular")
	public Formular sacuvajFormular(@Valid @RequestBody Formular formular, Principal principal) {
		
		return formularService.sacuvajFormular(formular, principal);
	}
	
	@Operation(summary = "Azuriranje formulara",
		       description = "Azurira se formular sa svojim svojstvima: id, naziv, vreme kreiranja, vreme "
		       		+ "poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "put", description = "PUT metode Heliant API")
	@PutMapping("/admin/izmeniFormular")
	public Formular izmeniFormular(@Valid @RequestBody Formular formular, Principal principal) {
		
		return formularService.izmeniFormular(formular, principal);
	}
	
	@Operation(summary = "Brisanje formulara",
		       description = "Brise se formular sa svojim svojstvima: id, naziv, vreme kreiranja, vreme "
		       		+ "poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "delete", description = "DELETE metode Heliant API")
	@DeleteMapping("/admin/obrisiFormular/{formularId}")
	public String izmeniFormular(@Parameter(
		       description = "ID formulara koji treba da se obrise",
		       required = true) @PathVariable("formularId") Long formularId) {
		
		formularService.obrisiFormularSaId(formularId);
		
		return "Formular uspesno obrisan!";
	}
	
	@Operation(summary = "Prikaz formulara sa paginacijom",
		       description = "Prikazuje se odredjeni broj formulara sa svojim svojstvima: id, naziv, vreme kreiranja, "
		       		+ "vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/prikaziFormulare/stranica/{brojStranice}")
	public List<Formular> prikaziFormulareSaPaginacijom(@Parameter(
		       description = "Broj stranice sa koje zele da se prikazu formulari",
		       required = true) @PathVariable (value = "brojStranice") int brojStranice) {
		
		int brojFormularaPoStranici = 5;
		
		Page<Formular> page = formularService.pronadjiFormulareSaPaginacijom(brojStranice, brojFormularaPoStranici);
		List<Formular> listaFormulara = page.getContent();
		
		return listaFormulara;
	}

}
