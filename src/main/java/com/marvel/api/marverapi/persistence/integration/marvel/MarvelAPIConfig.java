package com.marvel.api.marverapi.persistence.integration.marvel;
import com.marvel.api.marverapi.util.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Qualifier("md5Enconder")
    private PasswordEncoder md5Enconder;

    private long timestamp = new Date(System.currentTimeMillis()).getTime();
    private String publicKey = "fbea535a794778a54dc562fff5826bdb";
    private String privateKey = "7ea17d9c92ee4e585f4b9f0082912f9c9999e5f2";

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
