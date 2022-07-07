package com.example.mindaid.Controller;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Model.Concern;
import com.example.mindaid.Model.Login;
import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.ConcernRepository;
import com.example.mindaid.Repository.UserRepository;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.UserService;
import com.example.mindaid.Service.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
@Controller
public class IndexController {
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


    @GetMapping("/home")
    public String getHome(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "index";
    }
    //log in area start
    @GetMapping("/login")
    public String getLogin(Model model) {
        Login login=new Login();
        List<Login> loginList=new ArrayList<>();
        model.addAttribute(login);
        model.addAttribute("loginList",loginList);
        return "login";
    }
    @PostMapping("/postLogin")
    public String postLogin(Model model,Login login) throws UnsupportedEncodingException {
        User user=new User();
        model.addAttribute(user);
        List<Login> loginList=new ArrayList<>();
        int loginValidate=userService.loginValidation(login);
        if (loginValidate==1) return "concern";
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
    @GetMapping("/concern")
    public String getConcern(Model model) {
        Concern concern=new Concern();
        ConcernDto concernDto=new ConcernDto();
        List<Concern>concernList=concernRepository.findAll();
        System.out.println(concernList.size());
        model.addAttribute(concernDto);
        model.addAttribute(concern);
        model.addAttribute("concernList",concernList);
        return "concernLive";
    }
    @PostMapping("/submitconcern")
    public String postConcern(ConcernDto concernDto,Model model){
        System.out.println("concern:"+concernDto.concerns[1]);
        System.out.println("concern:"+concernDto.concerns[2]);
        System.out.println(concernDto.concernL.size());
        return "dummy";
    }
}
