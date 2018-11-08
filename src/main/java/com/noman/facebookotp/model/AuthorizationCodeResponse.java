package com.noman.facebookotp.model;

import lombok.Data;

@Data
public class AuthorizationCodeResponse {
    private AccessToken accessToken;
    private String error;
    private Phone phone;
    private String response;
}