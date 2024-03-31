package com.heliant.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ApiErrorResponse {

    private int code;
    private String opis;
    private String entitet;
    private String svojstvoEntiteta;
    private String narusenaValidacija;

}
