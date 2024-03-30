package com.heliant.enums;

import lombok.Getter;

@Getter
public enum Tip {
	
	TEKST ("Tekst"),
	BROJ ("Broj");
	
	private String label;

	private Tip(String label) {
		this.label = label;
	}

}
