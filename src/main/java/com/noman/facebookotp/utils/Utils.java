package com.noman.facebookotp.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

    private static String url="https://graph.accountkit.com/v1.3/access_token?grant_type=authorization_code&code=%s&access_token=AA|%s|%s";


    private static String appId ="624430304620726";

    private static String appSecret="592ca9ff3ce287ca700403b8ecbb99d6";

    public static void main(String[] args) {
        String accessToken = "EMAWe2ROyIZBFo09I5QMlbO9xoOZBu9LrXelvAINrUREJ1ZB4FWCOMOxHvRqn0HAuLZBZAxMybRw6YQ8XxRtDMAlSf43EZCbn8vUQrOnsInscJ5nGMH4fN5UHeQWqA48RXbV57yTVtzs1nVAg4JUZBw94ptZCmuMXA6CoZD";
        String appSecret = "592ca9ff3ce287ca700403b8ecbb99d6";
        secretKey(appSecret,accessToken);
        System.out.println(urlFormation());
    }


   /**--------------- Creating Hash Secret Key -----------------**/
    public static String secretKey(String secretkey,String secretMessage){
        try{

            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secretkey.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);

//            String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(secretMessage.getBytes()));
//            System.out.println(hash);
            // todo hex conversion of binary data
//            System.out.println();
            return Hex.encodeHexString(sha256_HMAC.doFinal(secretMessage.getBytes()));
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**----------------------- url Formation Check -------------------**/

    public static String urlFormation(){
        String authorizationCode= "AQD72-_7PUUuu3gC8LfKL78TNbXD8VUiybLx-LtY2ekKt5PQLGWNfIPANhxUobTHJl2siSF9S4sGaF8FuZh-7XtRFos2datgOEK5zFkZBRTKQnn8YdsOb9Vt3HmPdZij4zSPk6X9N1RYf1mpQnKuKgKq4FkcS7NU3ic6yR0JMzQciSkDgO7wHW4xUlnROjEZ0tJ0tMfLm5oudlkqf_Y00GNuiwdahAA3IbymR9Xo0AFIpQL1tpX_YjAa9ZqP6X99DExJqXAHgR59goBauwaPWaBg";
        return String.format(url,authorizationCode,appId,appSecret);
    }
}