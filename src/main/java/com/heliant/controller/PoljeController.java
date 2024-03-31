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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
public class PoljeController {
	
	@Autowired
	private PoljeService poljeService;
	
	@Operation(summary = "Prikaz svih polja svih formulara",
		       description = "Prikazuju se sva polja svih formulara sa svojim svojstvima: id, naziv, prikazni redosled, "
		       		+ "tip, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji "
		       		+ "ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/prikaziSvaPolja")
	public List<Polje> prikaziSvaPolja() {
		
	    return poljeService.prikaziSvaPolja();
	}
	
	@Operation(summary = "Prikaz svih polja odredjenih formulara",
		       description = "Prikazuju se polja odredjenih formulara sa svojim svojstvima: id, naziv, prikazni "
		       		+ "redosled, tip, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i "
		       		+ "korisnik koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/prikaziSvaPoljaFormulara/{formularId}")
	public List<Polje> prikaziSvaPolja(@Parameter(
		       description = "ID formulara cija polja treba da se prikazu",
		       required = true) @PathVariable("formularId") Long formularId) {
		
	    return poljeService.prikaziSvaPoljaFormulara(formularId);
	}
	
	@Operation(summary = "Kreiranje polja za odredjeni formular",
		       description = "Kreira se polje za odredjeni formular sa svojim svojstvima: id, naziv, prikazni redosled, "
		       		+ "tip, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji "
		       		+ "ga je poslednji azurirao.")
	@Tag(name = "post", description = "POST metode Heliant API")
	@PostMapping("/admin/sacuvajPolje")
	public Polje sacuvajPolje(@Valid @RequestBody Polje polje, Principal principal) {
		
		return poljeService.sacuvajPolje(polje, principal);
	}
	
	@Operation(summary = "Azuriranje polja za odredjeni formular",
		       description = "Azurira se polje za odredjeni formular sa svojim svojstvima: id, naziv, prikazni redosled, "
		       		+ "tip, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji "
		       		+ "ga je poslednji azurirao.")
	@Tag(name = "put", description = "PUT metode Heliant API")
	@PutMapping("/admin/izmeniPolje")
	public Polje izmeniPolje(@Valid @RequestBody Polje polje, Principal principal) {
		
		return poljeService.izmeniPolje(polje, principal);
	}
	
	@Operation(summary = "Brisanje polja za odredjeni formular",
		       description = "Brise se polje za odredjeni formular sa svojim svojstvima: id, naziv, prikazni redosled, "
		       		+ "tip, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik koji "
		       		+ "ga je poslednji azurirao.")
	@Tag(name = "delete", description = "DELETE metode Heliant API")
	@DeleteMapping("/admin/obrisiPolje/{poljeId}")
	public String obrisiPolje(@Parameter(
		       description = "ID polja koje treba da se obrise",
		       required = true) @PathVariable("poljeId") Long poljeId) {
		
		poljeService.obrisiPoljeSaId(poljeId);
		
		return "Polje uspesno obrisano!";
	}

}
