package com.example.mindaid.Controller;

import com.example.mindaid.Dto.*;
import com.example.mindaid.Model.*;
import com.example.mindaid.Repository.*;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
public class UserProfileController {
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
    PaymentRepository paymentRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @Autowired
    SessionValidatorService sessionValidatorService;

    @GetMapping("/user-profile")
    public String getUserProfile(Model model, HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "pending","user",httpSession);
        User user=userRepository.findByUserId(Integer.parseInt(((List<String>)httpSession.getAttribute("userInfo")).get(0)));
        model.addAttribute("userName",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/upcoming_appoinments")
    public String getUpcomingAppointments(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "approved","user",httpSession);
        User user=userRepository.findByUserId(Integer.parseInt(((List<String>)httpSession.getAttribute("userInfo")).get(0)));
        model.addAttribute("username",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/ongoing_appoinments")
    public String getOngoingAppointments(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1, "approved","user",httpSession);
        User user=userRepository.findByUserId(Integer.parseInt(((List<String>)httpSession.getAttribute("userInfo")).get(0)));
        model.addAttribute("username",user.getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/previous_appoinments")
    public String getPrevAppointments(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,0, "approved","user",httpSession);
        User user=userRepository.findByUserId(Integer.parseInt(((List<String>)httpSession.getAttribute("userInfo")).get(0)));
        model.addAttribute("username",((List<String>)httpSession.getAttribute("userInfo")).get(1));
        String status="Previous";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/pending_appoinments")
    public String getPendingAppointments(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2 ,"pending","user",httpSession);
        User user=userRepository.findByUserId(Integer.parseInt(((List<String>)httpSession.getAttribute("userInfo")).get(0)));
        model.addAttribute("username",((List<String>)httpSession.getAttribute("userInfo")).get(1));
        String status="Pending";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @PostMapping("/connect-session")
    public String joinSession(Model model,ScheduleDto scheduleDto,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Payment> paymentList=paymentRepository.findByPaymentId(scheduleDto.getPaymentId());
        String sessionlink= paymentList.get(0).getSessionLink();
        String usertype="user";
        int disableFlag=1;
        model.addAttribute("paymentDto",paymentList.get(0));
        model.addAttribute("usertype",usertype);
        model.addAttribute("sessionlink",sessionlink);
        model.addAttribute("disableFlag", disableFlag);
        if(paymentList.get(0).getContactMedia().equals("message")) return "messaging";
        else return "live";
    }

    @GetMapping(value = "/score-submit/{score}/{docId}")
    public String getUserProfile(@PathVariable("score") String score,@PathVariable("docId") String docId,Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        System.out.println(score);
        System.out.println(docId);
        List<Doctors>doctorsList=doctorsRepository.findByDocId(Integer.parseInt(docId));
        double d=Double.parseDouble(doctorsList.get(0).getRatings())*doctorsList.get(0).getPatientCount();
        doctorsList.get(0).setPatientCount(doctorsList.get(0).getPatientCount()+1);
        d=d+Double.parseDouble(score+".0");
        d=d/doctorsList.get(0).getPatientCount();
        String rating=String.format("%.1f", d);
        doctorsList.get(0).setRatings(rating);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "pending","user",httpSession);
//        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("userName",((List<String>)httpSession.getAttribute("userInfo")).get(1));
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }

    @GetMapping("/ratings")
    public String getRatings(Model model,HttpSession httpSession){

        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        return "ratings";
    }
}
