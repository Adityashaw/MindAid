package com.example.mindaid.Request;

import org.springframework.stereotype.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class Signup_request {
        public Boolean emailCheck(String email){
            Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(email);
            if(m.find()) return true;
            else return false;
        }
        public Boolean passCheck(String pass, String confirm_pass){
            if (pass.equals(confirm_pass))
                return true;
            else return false;
        }


}
