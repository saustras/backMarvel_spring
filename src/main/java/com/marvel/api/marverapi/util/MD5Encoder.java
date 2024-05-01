package com.marvel.api.marverapi.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("md5Encoder")
public class MD5Encoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return "DigestUtil.md5DigestAsHex(rawPassword.toString().getBytes())";
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(this.encode(rawPassword));
    }
}
