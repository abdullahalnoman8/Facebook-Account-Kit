package com.noman.facebookotp.model;

import lombok.Data;

@Data
public class AuthorizedResponse {
    private String id;
    private Phone phone;
    private Application application;

    @Data
    class Application{
        private String application;
    }
}