package com.example.mindaid.Controller;
import com.example.mindaid.Dto.*;
import com.example.mindaid.Model.*;
import com.example.mindaid.Repository.*;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import com.example.mindaid.Util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Autowired
    DoctorsSchedulingService doctorsSchedulingService;

    @GetMapping("/doctor-profile")
    public String getDoctorProfile(Model model){
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1, "approved","doctor");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
//        model.addAttribute("username",user.getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfile";
    }
    @GetMapping("/schedule-form")
    public String getScheduleForm(Model model){
        TemporaryObjectHoldService temporaryObjectHoldService=new TemporaryObjectHoldService();
        String status="something";
        model.addAttribute(temporaryObjectHoldService);
        model.addAttribute("status","something");
        return "scheduleform";
    }
    @PostMapping("/submitschedule")
    public String postScheduleForm(Model model,TemporaryObjectHoldService temporaryObjectHoldService){
        System.out.println(temporaryObjectHoldService.scheduleList.get(0));
        String status="something";
        model.addAttribute("status","something");
        return "scheduleform";
    }

    @GetMapping("/upcoming_doctor_appoinments")
    public String getUpcomingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,2, "approved","doctor");
        List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
        model.addAttribute("username",doctorsList.get(0).getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfile";
    }
    @GetMapping("/ongoing_doctor_appoinments")
    public String getOngoingAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,1, "approved","doctor");
        List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
        model.addAttribute("username",doctorsList.get(0).getName());
        String status="Ongoing";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfile";
    }
    @GetMapping("/previous_doctor_appoinments")
    public String getPrevAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,0, "approved","doctor");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
        model.addAttribute("username",doctorsList.get(0).getName());
        String status="Previous";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfile";
    }

    @GetMapping("/doctor-form")
    public String getDoctorProfile(Model model, DoctorsRepository doctorsRepository){
        Doctors doctors=new Doctors();
        model.addAttribute(doctors);
        return "doctorForm";
    }
    @PostMapping("/process-doctors-register")
    public String postDoctorProfile(Model model,Doctors doctors, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        doctors.setApproval("pending");
        LocalDate date=LocalDate.now();
        doctors.setAppliedDate(date.toString());
        doctors.setRatings("0.0");
        doctors.setPatientCount(0);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        doctors.setPhotos(fileName);
        Doctors savedDoctors= doctorsRepository.save(doctors);
        String uploadDir = "D:\\THERAPjavafest\\MindAid\\Gitversion\\MindAid\\src\\main\\resources\\static\\assets\\user-photos\\" + savedDoctors.getDocId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "doctorFormProcess";
    }
    @GetMapping("/your-schedule")
    public String getYourSchedule(Model model){
        List<Login> schedulemessageList= new ArrayList<>();
        model.addAttribute("schedulemessageList",schedulemessageList);
        return doctorsSchedulingService.getSetDoctorsSchedule(model);

    }
    @PostMapping("/submit-schedule")
    public String postYourSchedule(Model model, DoctorsScheduleDto doctorsScheduleDto){
        doctorsSchedulingService.updateSchedule(doctorsScheduleDto);
        List<Login> schedulemessageList= new ArrayList<>();
        Login login=new Login();
        login.setMessage("Your request has been sent for admin verification! Please wait for a while");
        model.addAttribute("schedulemessageList",schedulemessageList);
        return doctorsSchedulingService.getSetDoctorsSchedule(model);
    }
    @PostMapping("/connect-session-doc")
    public String joinSession(Model model,ScheduleDto scheduleDto){
        List<Payment> paymentList=paymentRepository.findByPaymentId(scheduleDto.getPaymentId());
        String sessionlink= paymentList.get(0).getSessionLink();
        String usertype="doctor";
        model.addAttribute("paymentDto",paymentList.get(0));
        model.addAttribute("usertype",usertype);
        model.addAttribute("sessionlink",sessionlink);
        if(paymentList.get(0).getContactMedia().equals("message")) return "messaging";
        else return "live";
    }
    @GetMapping("/doc-profile")
    public String getDoctorProfileEdit(Model model){
        List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
        model.addAttribute("profile",doctorsList.get(0));
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfileEdit";
    }

    @PostMapping("/postEdit")
    public String postEdit(Model model,Doctors doctors){
        List<Doctors>doctorsList=doctorsRepository.findByDocId(temporaryObjectHoldService.userDto.getUserId());
        doctorsList.get(0).setSpeciality(doctors.getSpeciality());
        doctorsList.get(0).setDescription(doctors.getDescription());
        doctorsList.get(0).setName(doctors.getName());
        doctorsList.get(0).setMobile(doctors.getMobile());
        doctorsList.get(0).setLoginEmail(doctors.getLoginEmail());
        doctorsList.get(0).setEducation(doctors.getEducation());
        doctorsList.get(0).setExperience(doctors.getExperience());
        doctorsList.get(0).setAge(doctors.getAge());
        doctorsList.get(0).setGender(doctors.getGender());
        doctorsRepository.save(doctorsList.get(0));
        model.addAttribute("profile",doctorsList.get(0));
        model.addAttribute("userName",temporaryObjectHoldService.getUserDto().getName());
        return "doctorProfileEdit";
    }

}
