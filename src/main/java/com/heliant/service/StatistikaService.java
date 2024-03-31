package com.heliant.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.heliant.model.FormularPopunjen;
import com.heliant.model.Statistika;
import com.heliant.repository.StatistikaRepository;

@Service
@EnableScheduling
public class StatistikaService {
	
	@Autowired
	private StatistikaRepository statistikaRepository;
	
	@Autowired
	private FormularService formularService;
	
	@Scheduled(cron = "0 0 0 * * *")
	public void prebrojPopunjeneFormulare() {
		List<FormularPopunjen> popunjeniFormulariDanas = formularService.vratiSvePopunjeneFormulareDanas(LocalDate.now().minusDays(1).atStartOfDay(), LocalDate.now().minusDays(1).atTime(LocalTime.MAX));
	
		Statistika statistika = new Statistika();
		statistika.setDatum(LocalDate.now().minusDays(1));
		statistika.setBrojPopunjenihFormulara(popunjeniFormulariDanas.size());
		
		statistikaRepository.save(statistika);
		
	}

}
