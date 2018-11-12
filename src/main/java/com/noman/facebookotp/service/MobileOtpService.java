package com.noman.facebookotp.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.noman.facebookotp.model.AccessToken;
import com.noman.facebookotp.model.AuthorizationCodeResponse;
import com.noman.facebookotp.model.AuthorizedResponse;
import com.noman.facebookotp.model.Phone;
import javafx.scene.shape.Circle;
import jdk.nashorn.internal.codegen.MapCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
        log.info("Response Code:{}",responseEntity.getBody());
        return responseEntity.getBody();
    }

    public Phone accessTokenResponse(String accessToken,String appSecretProof){
        RestTemplate restTemplate = restTemplateBuilder.build();
        String accessTokenValidationUrl = "https://graph.accountkit.com/v1.3/me/?access_token=%s&appsecret_proof=%s";
        String accessTokenUrl = String.format(accessTokenValidationUrl,accessToken,appSecretProof);
        log.info("User Access Token Validation URL: {}",accessTokenUrl);
        ParameterizedTypeReference<Map<String,Object>> parameterizedType = new ParameterizedTypeReference<Map<String, Object>>() {};

//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(accessTokenUrl,String.class);

        ResponseEntity<Map<String,Object>> responseEntity = restTemplate.exchange(accessTokenUrl, HttpMethod.GET, null,parameterizedType);
        log.info("Response Entity Body: {}",responseEntity.getBody());
        log.info("Response Entity: {}",responseEntity);
        try{
            Gson gson = new GsonBuilder().create();
            String jsonString = gson.toJson(responseEntity.getBody(),parameterizedType.getType());
            log.info("JSON String: {}",jsonString);

//            Map<String,String> map = gson.fromJson(jsonString,Map.class);
//            log.info("Map Data: {}",map);
//            for(Map.Entry entry : map.entrySet()){
//                if(entry.getKey().equals("phone")){
//                    Phone phone = new Phone();
//                    Type type = new TypeToken<Map<String,Object>>(){}.getType();
//                    Map<String,String> phoneData = gson.fromJson(entry.getValue().toString(),type);
//                    log.info("Phone Key Value Data: {}",phoneData);
//                    phoneData.entrySet().stream().forEach(e ->
//                            System.out.println("Phone Data Key : " + e.getKey() + " Value: " + e.getValue()));
//                }
//            }
//            map.entrySet().stream().forEach(e ->
//                    System.out.println("Key : " + e.getKey() + " value : " + e.getValue())
//            );
            AuthorizedResponse phone = gson.fromJson(jsonString,AuthorizedResponse.class);
            log.info("Phone Data: {}",phone);


        }catch (Exception ex){
            ex.printStackTrace();
        }
//        Phone phone = gson.fromJson(responseEntity,Phone.class);
//        log.info("Response Code:{}",responseEntity.getStatusCode());
//        log.info("Response Body:{}",responseEntity.getBody());
        return null;
    }


}