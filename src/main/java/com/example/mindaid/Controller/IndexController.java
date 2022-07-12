package com.example.mindaid.Controller;
import com.example.mindaid.Dto.ChooseDto;
import com.example.mindaid.Dto.ConcernDto;
import com.example.mindaid.Dto.DoctorsDto;
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
        return "concern";
    }
    @PostMapping("/submitconcern")
    public String postConcern(ConcernDto concernDto,Model model){
        Choose choose=new Choose();
        ChooseDto chooseDto=new ChooseDto();
        model.addAttribute(chooseDto);
        temporaryConcernService.chooseList.clear();
        temporaryConcernService.chooseList.add(concernDto);
        return "choose";
    }
    @GetMapping("/doctors-list")
    public String getdoctorList(Model model){
        return "doctorsListNew";

    }
    @GetMapping("/doctors-details")
    public String getdoctorDetails(Model model){
        return "doctorsDetails";

    }
    @GetMapping("/booking")
    public String getBooking(Model model){
        return "bookingPage";

    }

    @GetMapping("/choose")
    public String getChoose(Model model){
        return "choose";
    }
    @PostMapping("/doctors-list")
    public String postChoose(Model model, ChooseDto chooseDto){
        List<DoctorsDto> doctorsList=doctorListService.getDoctorList(temporaryConcernService.chooseList.get(0),chooseDto);
        temporaryObjectHoldService.doctorsDtoList.clear();
        temporaryObjectHoldService.doctorsDtoList.add(doctorsList);
        System.out.println(doctorsList);
        System.out.println(doctorsList.get(0));
        System.out.println("choose: "+chooseDto.contactMedia);
        model.addAttribute("doctorsList",doctorsList);
        DoctorsDto doctorDto=new DoctorsDto();
        model.addAttribute(doctorDto);
        return "doctorsListNew";
    }
    @PostMapping("/postdoctorlist")
    public  String postdoclist(Model model,DoctorsDto docDto){
        DateTimeFormatter formatter1=DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("HH");
        DateTimeFormatter formatter3=DateTimeFormatter.ofPattern("mm");
        List<LocalTime>scheduleTimes=new ArrayList<>();
        List<String>scheduleTimesStr=new ArrayList<>();
        for (DoctorsDto doctorsDto : temporaryObjectHoldService.getDoctorsDtoList().get(0)){
            if (docDto.doc_id==doctorsDto.doc_id){
                Time time=doctorsDto.scheduleTimeStart;
                LocalTime localTime=time.toLocalTime();
                List<TemporaryObjectHoldService>scheduleTimeAndTimeStr=new ArrayList<>();
                TemporaryObjectHoldService obj1=new TemporaryObjectHoldService();
                TemporaryObjectHoldService obj2=new TemporaryObjectHoldService();
                TemporaryObjectHoldService obj3=new TemporaryObjectHoldService();
                obj1.setScheduleTime(localTime);
                obj2.setScheduleTime(localTime.plusHours(1));
                obj3.setScheduleTime(localTime.plusHours(2));
                scheduleTimes.add(localTime);
                scheduleTimes.add(localTime.plusHours(1));
                scheduleTimes.add(localTime.plusHours(2));
                String strr1=localTime.toString();
                String strr2=(localTime.plusHours(1)).toString();
                String strr3=(localTime.plusHours(2)).toString();
                String scheduleTimestr1=schedulingService.AmPmFormetter(strr1);
                String scheduleTimestr2=schedulingService.AmPmFormetter(strr2);
                String scheduleTimestr3=schedulingService.AmPmFormetter(strr3);
                obj1.setScheduleTimeStr(scheduleTimestr1);
                obj2.setScheduleTimeStr(scheduleTimestr2);
                obj3.setScheduleTimeStr(scheduleTimestr3);
                scheduleTimesStr.add(scheduleTimestr1);
                scheduleTimesStr.add(scheduleTimestr2);
                scheduleTimesStr.add(scheduleTimestr3);
//                scheduleTimeAndTimeStr.add(obj1);
//                scheduleTimeAndTimeStr.add(obj2);
//                scheduleTimeAndTimeStr.add(obj3);
                Collections.addAll(scheduleTimeAndTimeStr,obj1,obj2,obj3);
                String str=localTime.toString()+"-"+(localTime.plusMinutes(59).toString());
                model.addAttribute("scheduleTimeAndTimeStr",scheduleTimeAndTimeStr);
                model.addAttribute("scheduleTimesStr",scheduleTimesStr);
                model.addAttribute("scheduleTimes",scheduleTimes);
                model.addAttribute("doctorsDto",doctorsDto);
                break;
            }
        }
        List<LocalDate>scheduleDays=new ArrayList<>();
        LocalDate date=LocalDate.now().plusDays(1);
        scheduleDays.add(date);
        LocalDate dateNext= LocalDate.now().plusDays(2);
        scheduleDays.add(dateNext);
        LocalDate dateNextNext= LocalDate.now().plusDays(3);
        scheduleDays.add(dateNextNext);
        model.addAttribute("scheduleDays",scheduleDays);
        return "doctorsDetails";
    }
    @PostMapping("/payment")
    public  String postdocDetails(Model model,DoctorsDto doctorsDto){
        return "dummy";
    }



}
