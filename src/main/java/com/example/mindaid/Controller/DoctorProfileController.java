package com.example.mindaid.Controller;
import com.example.mindaid.Dto.*;
import com.example.mindaid.Model.*;
import com.example.mindaid.Repository.*;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class DoctorProfileController {
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
    AdminService adminService;

    @GetMapping("/doctor-profile")
    public String getDoctorProfile(Model model){
        return "doctorProfile";
    }
    @GetMapping("/upcoming_doctor_appoinments")
    public String getUpcomingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "approved");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/ongoing_doctor_appoinments")
    public String getOngoingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1, "approved");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }
    @GetMapping("/previous_doctor_appoinments")
    public String getPrevAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,0, "approved");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Previous";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        return "userProfile";
    }

    @GetMapping("/doctor-form")
    public String getDoctorProfile(Model model, DoctorsRepository doctorsRepository){
        Doctors doctors=new Doctors();
        model.addAttribute(doctors);
        return "doctorForm";
    }
    @PostMapping("/process-doctors-register")
    public String postDoctorProfile(Model model,Doctors doctors){
        doctors.setApproval("pending");
        LocalDate date=LocalDate.now();
        doctors.setAppliedDate(date.toString());
        doctorsRepository.save(doctors);
        return "doctorFormProcess";
    }
}
