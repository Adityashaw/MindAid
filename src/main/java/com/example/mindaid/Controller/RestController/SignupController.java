package com.example.mindaid.Controller.RestController;

import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.ConcernRepository;
import com.example.mindaid.Repository.DoctorConcernRepository;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.UserRepository;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class SignupController {
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

//Registration start
    @GetMapping("/register")
    public String getIndex_reg(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "register";
    }
    @PostMapping("/processregister")
    public String postSignup(User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
        if ((signup_request.emailCheck(user.email)) && (signup_request.passCheck(user.password, user.confirmPassword))) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            user.setUserType("user");
            userRepository.save(user);
            emailVerificationService.register(user, getSiteURL(request));
            return "register_success";
        } else return "dummy";
    }
    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (emailVerificationService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
    //Registration End
}
