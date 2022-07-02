package com.example.mindaid.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
@Service

public class EncodePassword  {
    public String encodePassword(String password) throws UnsupportedEncodingException{
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    public boolean matchPassword(String password,String encodedPassword){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        password= passwordEncoder.encode(password);
        System.out.println(encodedPassword);
        System.out.println("encoded "+password);
        boolean check=passwordEncoder.matches(password, encodedPassword);
        System.out.println(check);
        return check;
    }

}
