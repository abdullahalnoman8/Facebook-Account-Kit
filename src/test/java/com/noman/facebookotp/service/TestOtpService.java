package com.noman.facebookotp.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Test
    public void testOtp(){
        String code = "AQBuHgYb1MMjEEJcwUoYT9Am3Y3yy4XEVHWMTf5L13AhhOZUQ-GKNk6qjXA4HZ1HSwGOl7vWopQgV3-83UxYDJC4CJyo7gNcfWbk39if-INqu7018mNr217YfSxYgF1-DN3HL3OXIG_fnfNyPTK5QcqkygfsobSjPImvnkjNYZ7H8hEUxN0RPLe4CstcET85hn_GFJkOUiBUbnHJubUs6wj9wAxroV_Sw6Eo-7bVwR6PaA2-GW9e9r8-eY56K1FZiCUcUufmx1VnNJbfbF0NT6Bs";
        mobileOtpService.findAccessToken(code);
    }

}