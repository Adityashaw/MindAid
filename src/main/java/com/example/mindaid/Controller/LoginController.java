package com.example.mindaid.Controller;

import com.example.mindaid.Dto.ScheduleDto;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Model.Login;
import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.ConcernRepository;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.UserRepository;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConcernRepository concernRepository;
    @Autowired
    Signup_request signup_request;
    @Autowired
    EmailVerificationService emailVerificationService;
    @Autowired
    UserService userService;
    @Autowired
    DoctorListService doctorListService;
    @Autowired
    DoctorConcernRepository doctorConcernRepository;
    @Autowired
    DoctorsRepository doctorsRepository;
    @Autowired
    TemporaryConcernService temporaryConcernService;
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    SchedulingService schedulingService;
    @Autowired
    ConcernService concernService;
    @Autowired
    MailSendingService mailSendingService;
    @Autowired
    AdminService adminService;



    //log in area start
    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session) {
        System.out.println(session.getId());
        Login login=new Login();
        List<Login> loginList=new ArrayList<>();
        model.addAttribute(login);
        model.addAttribute("loginList",loginList);
        return "login";
    }

    @PostMapping("/postLogin")
    public String postLogin(Model model,Login login,HttpSession session) throws UnsupportedEncodingException {
        System.out.println(session.getId());
        User user=new User();
        model.addAttribute(user);
        List<Login> loginList=new ArrayList<>();
        int loginValidate=userService.loginValidationAndUserIdTransfer(login,model,session);
//        System.out.println("sesion er lilakhela:"+((List<String>)session.getAttribute("userInfo")).size());
        if (loginValidate==1) {
            if(((List<String>)session.getAttribute("userInfo")).get(2).equals("doctor")){
                List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "approved","doctor",session);
//                User user1=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
                List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
                String status="Upcoming";
                model.addAttribute("status",status);
                model.addAttribute("scheduleInfoList",scheduleInfoList);
                model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
                return "doctorProfile";
            }
            else if (((List<String>)session.getAttribute("userInfo")).get(2).equals("admin")){
                return adminService.getAllDoctors(model);
            }
            else{
                concernService.getAndSetConcernList(model);
                return "concern";

            }

        }
        else if(loginValidate==2){
            login.setMessage("Please verify your account from email!!");
            loginList.add(login);
            model.addAttribute("loginList", loginList);
            return "login";
        }
        else {
            login.setMessage("Wrong Credentials!!");
            loginList.add(login);
            model.addAttribute("loginList", loginList);
            return "login";
        }
    }
    //log in area end

    //Logout area Start
    @GetMapping("/logout")
    public String getLogOut(Model model,HttpSession httpSession){
        httpSession.invalidate();
//        System.out.println(httpSession.getId());
        Login login=new Login();
        List<Login> loginList=new ArrayList<>();
        model.addAttribute(login);
        model.addAttribute("loginList",loginList);
        return "login";
    }
    //Logout area end
}
