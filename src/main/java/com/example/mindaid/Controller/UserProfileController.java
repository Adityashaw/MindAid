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

    @GetMapping("/user-profile")
    public String getUserProfile(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1);
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("userName",user.getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/upcoming_appoinments")
    public String getUpcomingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2);
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/ongoing_appoinments")
    public String getOngoingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1);
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/previous_appoinments")
    public String getPrevAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,0);
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Previous";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @PostMapping("/connect-session")
    public String joinSession(Model model,ScheduleDto scheduleDto){
        List<Payment> paymentList=paymentRepository.findByPaymentId(scheduleDto.getPaymentId());
        String sessionlink= paymentList.get(0).getSessionLink();
        model.addAttribute("sessionlink",sessionlink);
        return "live";
    }
}
