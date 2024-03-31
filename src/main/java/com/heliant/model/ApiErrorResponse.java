package com.heliant.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ApiErrorResponse {

    private int code;
    private String opis;
    private String originalnaPoruka;

}
