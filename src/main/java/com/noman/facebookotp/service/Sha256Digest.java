package com.noman.facebookotp.service;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Sha256Digest {
    Mac mac;
    @Value("accountkit.appsecret")
    private String appSecret;

    private static final String APP_SECRET ="592ca9ff3ce287ca700403b8ecbb99d6";
    Sha256Digest() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        this(APP_SECRET);
    }

    Sha256Digest(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec sk = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8.toString()), "HmacSHA256");
        mac = Mac.getInstance("HmacSHA256");
        mac.init(sk);
    }

    String hash(String msg) throws UnsupportedEncodingException {
        return HexConverter.bytesToHex(mac.doFinal(msg.getBytes(StandardCharsets.UTF_8.toString())));
    }

    public static void main(String[] args) throws Exception {

        String token="EMAWe2ROyIZBFo09I5QMlbO9xoOZBu9LrXelvAINrUREJ1ZB4FWCOMOxHvRqn0HAuLZBZAxMybRw6YQ8XxRtDMAlSf43EZCbn8vUQrOnsInscJ5nGMH4fN5UHeQWqA48RXbV57yTVtzs1nVAg4JUZBw94ptZCmuMXA6CoZD";
        System.out.println(new Sha256Digest().hash(token));
    }
}