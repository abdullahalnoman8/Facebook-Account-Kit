package com.noman.facebookotp.model;

import lombok.Data;

@Data
public class Phone {
    private String id;
    private String number;
    private String countryPrefix;
    private String nationalNumber;

}