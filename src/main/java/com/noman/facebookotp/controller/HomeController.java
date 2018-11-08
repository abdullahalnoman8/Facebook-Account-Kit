package com.noman.facebookotp.controller;

import com.noman.facebookotp.model.AccessToken;
import com.noman.facebookotp.model.Phone;
import com.noman.facebookotp.service.MobileOtpService;
import com.noman.facebookotp.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
public class HomeController{

    @Autowired
    private MobileOtpService mobileOtpService;

    @Value("${accountkit.appsecret}")
    private String appSecret;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/otpData")
    @ResponseBody
    public String otpData(HttpServletRequest request, Model model){
        String code = request.getParameter("code");
        String csrf = request.getParameter("csrf");
        log.info("CSRF: {} , Code: {}",csrf,code);
//        log.info("Access Token: {}",mobileOtpService.findAccessToken(code));
        AccessToken accessToken = mobileOtpService.findAccessToken(code);
        log.info("Access Token: {}",accessToken);
        String appSecretProof = Utils.secretKey(appSecret,accessToken.getAccess_token());
        log.info("AppSecret Proof: {}",appSecretProof);
        Phone phone = mobileOtpService.accessTokenResponse(accessToken.getAccess_token(),appSecretProof);
        log.info("Phone Information: {}",phone);
        return "OtpData";
    }

}