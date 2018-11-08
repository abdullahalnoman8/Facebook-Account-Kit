package com.noman.facebookotp.model;

import lombok.Data;

@Data
public class Phone {
    private String id;
    private String number;
    private String country_prefix;
    private String national_number;

}