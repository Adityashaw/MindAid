package com.example.mindaid.Service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class SessionValidatorService {

    public boolean sessionValidator(HttpSession httpSession){
        if ((List<String>)httpSession.getAttribute("userInfo")==null){
            return false;
        }
        else {
            return true;
        }
    }
}
