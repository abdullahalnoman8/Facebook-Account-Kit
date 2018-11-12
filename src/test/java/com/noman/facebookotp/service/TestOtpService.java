package com.noman.facebookotp.service;

import com.noman.facebookotp.model.Phone;
import com.noman.facebookotp.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@Slf4j
@WebAppConfiguration
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestOtpService {
    @Autowired
    MobileOtpService mobileOtpService;

    @Value("${accountkit.appsecret}")
    private String appSecret;

    @Test
    public void testOtp(){
        String code = "AQBuHgYb1MMjEEJcwUoYT9Am3Y3yy4XEVHWMTf5L13AhhOZUQ-GKNk6qjXA4HZ1HSwGOl7vWopQgV3-83UxYDJC4CJyo7gNcfWbk39if-INqu7018mNr217YfSxYgF1-DN3HL3OXIG_fnfNyPTK5QcqkygfsobSjPImvnkjNYZ7H8hEUxN0RPLe4CstcET85hn_GFJkOUiBUbnHJubUs6wj9wAxroV_Sw6Eo-7bVwR6PaA2-GW9e9r8-eY56K1FZiCUcUufmx1VnNJbfbF0NT6Bs";
        mobileOtpService.findAccessToken(code);
    }

    @Test
    public void testAuthorization(){
          String accessToken = "EMAWdXf29F1yhZBI8J5C5Vb2ZCT5rXR3LEPfvarcPz1mnZCibLqOcisWTw1Pie4PGP26T80gaCJNyn4We6kP5j3wZC8h07ZBzZCZAiMz3O2s0mEwHsndEZCs7c20oEgvZAajDa6v21NzJxRZAVZBKvZCxuiZC5Yjp0QNrMuyugZD";
        String appSecretProof = Utils.secretKey(appSecret,accessToken);
        Phone phone = mobileOtpService.accessTokenResponse(accessToken,appSecretProof);
        log.info("Data Phone:{}",phone);
    }

}