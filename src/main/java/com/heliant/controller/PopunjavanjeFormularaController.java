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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
public class PopunjavanjeFormularaController {
	
	@Autowired
	private FormularService formularService;
	
	@Operation(summary = "Prikaz svih popunjenih formulara",
		       description = "Prikazuju se svi popunjeni formulari sa svojim svojstvima: id, formular, sva popunjena "
		       		+ "polja, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik "
		       		+ "koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/prikaziSvePopunjeneFormulare")
	public List<FormularPopunjen> prikaziSvePopunjeneFormulare() {
		
	    return formularService.prikaziSvePopunjeneFormulare();
	}
	
	@Operation(summary = "Prikaz odredjenog popunjenog formulara",
		       description = "Prikazuju se odredjeni popunjeni formular sa svojim svojstvima: id, formular, sva popunjena "
		       		+ "polja, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik "
		       		+ "koji ga je poslednji azurirao.")
	@Tag(name = "get", description = "GET metode Heliant API")
	@GetMapping("/svi/vratiPopunjenFormularSaId/{formularPopunjenId}")
	public FormularPopunjen vratiFormularSaId(@Parameter(
		       description = "ID popunjenog formulara koji treba da se prikaze",
		       required = true) @PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
	    return formularService.vratiFormularPopunjenSaId(formularPopunjenId);
	}
	
	@Operation(summary = "Kreiranje popunjenog formulara",
		       description = "Kreira se popunjeni formular sa svojim svojstvima: id, formular, sva popunjena "
		       		+ "polja, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik "
		       		+ "koji ga je poslednji azurirao.")
	@Tag(name = "post", description = "POST metode Heliant API")
	@PostMapping("/svi/sacuvajPopunjenFormular")
	public FormularPopunjen sacuvajPopunjenFormular(@Valid @RequestBody FormularPopunjen formularPopunjen, Principal principal) {
		
		return formularService.sacuvajFormularPopunjen(formularPopunjen, principal);
	}
	
	@Operation(summary = "Azuriranje popunjenog formulara",
		       description = "Azurira se popunjeni formular sa svojim svojstvima: id, formular, sva popunjena "
		       		+ "polja, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik "
		       		+ "koji ga je poslednji azurirao.")
	@Tag(name = "put", description = "PUT metode Heliant API")
	@PutMapping("/svi/izmeniPopunjenFormular")
	public FormularPopunjen izmeniPopunjenFormular(@Valid @RequestBody FormularPopunjen formularPopunjen, Principal principal) {
		
		return formularService.izmeniFormularPopunjen(formularPopunjen, principal);
	}
	
	@Operation(summary = "Brisanje popunjenog formulara",
		       description = "Brise se popunjeni formular sa svojim svojstvima: id, formular, sva popunjena "
		       		+ "polja, vreme kreiranja, vreme poslednjeg azuriranja, korisnik koji ga je kreirao i korisnik "
		       		+ "koji ga je poslednji azurirao.")
	@Tag(name = "delete", description = "DELETE metode Heliant API")
	@DeleteMapping("/svi/obrisiPopunjenFormular/{formularPopunjenId}")
	public String obrisiPopunjenFormular(@Parameter(
		       description = "ID popunjenog formulara koji treba da se obrise",
		       required = true) @PathVariable("formularPopunjenId") Long formularPopunjenId) {
		
		formularService.obrisiFormularPopunjenSaId(formularPopunjenId);
		
		return "Popunjen formular uspesno obrisan!";
	}

}
