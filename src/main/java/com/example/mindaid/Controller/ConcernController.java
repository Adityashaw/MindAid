package com.example.mindaid.Controller;
import com.example.mindaid.Dto.ChooseDto;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Dto.UserDto;
import com.example.mindaid.Model.*;
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

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class ConcernController {
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
    SessionValidatorService sessionValidatorService;

    @GetMapping("/concern")
    public String getConcern(Model model,HttpSession httpSession) {
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
            UserDto userDto = new UserDto();
            userDto = temporaryObjectHoldService.getUserDto();
            concernService.getAndSetConcernList(model);
            model.addAttribute("userDto", userDto);
            return "concern";

    }
    @PostMapping("/submitconcern")
    public String postConcern(ConcernDto concernDto, UserDto userDto, Model model, HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        Choose choose=new Choose();
        ChooseDto chooseDto=new ChooseDto();
        temporaryConcernService.chooseList.clear();
        temporaryConcernService.chooseList.add(concernDto);
        List<Integer>concerns=new ArrayList<>();
        for (int i=0;i<concernDto.getConcerns().length;i++){
            concerns.add(concernDto.getConcerns()[i]);
        }
        httpSession.setAttribute("concerns",concerns);
        System.out.println((List<String>)httpSession.getAttribute("userInfo"));
        System.out.println((List<Integer>)httpSession.getAttribute("concerns"));
        //test
//        System.out.println(userDto.userId);
//        System.out.println(concernDto.getConcerns()[3]);
        //test end
        model.addAttribute(concernDto);
        model.addAttribute(chooseDto);
        model.addAttribute(userDto);
        return "choose";
    }
}
