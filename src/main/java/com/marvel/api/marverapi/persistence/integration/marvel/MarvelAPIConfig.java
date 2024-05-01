package com.marvel.api.marverapi.persistence.integration.marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MarvelAPIConfig {

    @Qualifier("md5Enconder")
    private PasswordEncoder md5Enconder;

    private long timestamp = new Date(System.currentTimeMillis()).getTime();
    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash(){
        String hashDecoded = Long.toString(timestamp).concat(privateKey).concat(publicKey);
        return md5Enconder.encode(hashDecoded);
    }

    public Map<String,String> getAuthenticationQueryParams() {

        Map<String,String> securityQueryParams = new HashMap<>();

        securityQueryParams.put("ts", Long.toString(this.timestamp));
        securityQueryParams.put("apikey", publicKey);
        securityQueryParams.put("hash", this.getHash());

        return securityQueryParams;
    }
}
