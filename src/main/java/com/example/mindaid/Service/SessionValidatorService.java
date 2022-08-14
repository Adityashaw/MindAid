package com.example.mindaid.Service;

import com.example.mindaid.Model.Login;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionValidatorService {

    public boolean userSessionValidator(HttpSession httpSession){
        if ((List<String>)httpSession.getAttribute("userInfo")==null){
            return true;
        }
        else if (((List<String>)httpSession.getAttribute("userInfo")).get(2).equals("user")){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean doctorSessionValidator(HttpSession httpSession){
        if ((List<String>)httpSession.getAttribute("userInfo")==null){
            return true;
        }
        else if (((List<String>)httpSession.getAttribute("userInfo")).get(2).equals("doctor")){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean adminSessionValidator(HttpSession httpSession){
        if ((List<String>)httpSession.getAttribute("userInfo")==null){
            return true;
        }
        else if (((List<String>)httpSession.getAttribute("userInfo")).get(2).equals("admin")){
            return false;
        }
        else {
            return true;
        }
    }

    public String loginPageReturn(Model model){
        Login login=new Login();
        List<Login> loginList=new ArrayList<>();
        model.addAttribute(login);
        model.addAttribute("loginList",loginList);
        return "login";
    }
}
