package com.noman.facebookotp.service;

import com.noman.facebookotp.model.AccessToken;
import com.noman.facebookotp.model.AuthorizationCodeResponse;
import com.noman.facebookotp.model.Phone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class MobileOtpService {

    @Value("${phone_user_access_token.url}")
    private String url;


    @Value("${account_kit.appId}")
    private String appId;
    @Value("${accountkit.appsecret}")
    private String appSecret;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public AccessToken findAccessToken(String authorizationCode){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String accessTokenUrl = String.format(this.url,authorizationCode.trim(),appId.trim(),appSecret.trim());
        System.out.println("Access Token URL: "+accessTokenUrl);
        ResponseEntity<AccessToken> responseEntity = restTemplate.getForEntity(accessTokenUrl,AccessToken.class);
        log.info("Response Code:{}",responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public Phone accessTokenResponse(String accessToken,String appSecretProof){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String accessTokenValidationUrl = "https://graph.accountkit.com/v1.3/me/?access_token=%s&appsecret_proof=%s";
        String accessTokenUrl = String.format(accessTokenValidationUrl,accessToken,appSecretProof);
        System.out.println("Access Token Validation URL: "+accessTokenUrl);
        ResponseEntity<Phone> responseEntity = restTemplate.getForEntity(accessTokenUrl,Phone.class);
        log.info("Response Code:{}",responseEntity.getStatusCode());
        return responseEntity.getBody();
    }


}