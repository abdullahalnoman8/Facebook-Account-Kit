package com.noman.facebookotp.model;

import lombok.Data;

@Data
public class AccessToken {
    private String id;
    private String access_token;
    private Integer token_refresh_interval_sec;
}