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
        model.addAttribute("username",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
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
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Upcoming";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
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
        return "doctorProfile";
    }
    @GetMapping("/previous_doctor_appoinments")
    public String getPrevAppointments(Model model){
        System.out.println(temporaryObjectHoldService.userDto.userId);
        List <ScheduleDto>scheduleInfoList=schedulingService.getcheduleInfo(model,0, "approved","doctor");
        User user=userRepository.findByUserId(temporaryObjectHoldService.userDto.userId);
        model.addAttribute("username",user.getName());
        String status="Previous";
        model.addAttribute("status",status);
        model.addAttribute("scheduleInfoList",scheduleInfoList);
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
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        doctors.setPhotos(fileName);
        Doctors savedDoctors= doctorsRepository.save(doctors);
        String uploadDir = "D:\\THERAPjavafest\\MindAid\\Gitversion\\MindAid\\src\\main\\resources\\static\\assets\\user-photos\\" + savedDoctors.getDocId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        return "doctorFormProcess";
    }
    @GetMapping("/your-schedule")
    public String getYourSchedule(Model model){
        DoctorsScheduleDto doctorsScheduleDto=new DoctorsScheduleDto();
        doctorsScheduleDto.setDocId(temporaryObjectHoldService.getUserDto().getUserId());
        List<DoctorsScheduleDto> doctorsScheduleDtoList=new ArrayList<>();
        List<Concern>concernList=concernRepository.findAll();
        for(int i=0;i<7;i++){
            DoctorsScheduleDto doctorsScheduleDto1=new DoctorsScheduleDto();
            doctorsScheduleDto1.setDayParameter(Integer.toString(i));
            if(i==0) doctorsScheduleDto1.setDay("Sunday");
            else if(i==1) doctorsScheduleDto1.setDay("Monday");
            else if(i==2) doctorsScheduleDto1.setDay("Tuesday");
            else if(i==3) doctorsScheduleDto1.setDay("Wednesday");
            else if(i==4) doctorsScheduleDto1.setDay("Thursday");
            else if(i==5) doctorsScheduleDto1.setDay("Friday");
            else doctorsScheduleDto1.setDay("Saturday");
            doctorsScheduleDtoList.add(doctorsScheduleDto1);
        }
        List<String> slotListMessage=new ArrayList<>();
        List<String> slotListLive=new ArrayList<>();
        for(int message=10;message<=19;message+=3){
            String m_time=message+":00:00";
            slotListMessage.add(m_time);
        }
        for(int live=10;live<=21;live++){
            String l_time=live+":00:00";
            slotListLive.add(l_time);
        }
        model.addAttribute("slotListMessage",slotListMessage);
        model.addAttribute("slotListLive",slotListLive);
        model.addAttribute("concernList",concernList);
        model.addAttribute("doctorsScheduleDtoList",doctorsScheduleDtoList);

        model.addAttribute(doctorsScheduleDto);


        //Display current Schedule
        List<Schedule>schedules=scheduleRepository.findByDocIdAndApproval(doctorsScheduleDto.getDocId(),"approved");
        List<Schedule> scheduleList=new ArrayList<>();
        for (Schedule schedule1:schedules){
            char[] scheduleDayParam=schedule1.getScheduleday_parameter().toCharArray();
            String[] scheduleDay=schedule1.getScheduleDay().split(",");
            for(int i=0;i<scheduleDay.length;i++) {
                char c=scheduleDayParam[i];
                Schedule schedule2=new Schedule();
                String scheduleTimestrts = "";

                String[] schedulesList = schedule1.getScheduleTimeStart().split(",");
                for (String st : schedulesList) {
                    String[] spliTedSchedule = st.split("~");
                    if (scheduleTimestrts.equals("") && Integer.parseInt(spliTedSchedule[0])==(Character.getNumericValue(c))) {
                        scheduleTimestrts = spliTedSchedule[1];
                    } else if(Integer.parseInt(spliTedSchedule[0])==(Character.getNumericValue(c))){
                        scheduleTimestrts = scheduleTimestrts + "," + spliTedSchedule[1];
                    }
                }
                schedule2.setScheduleTimeStart(scheduleTimestrts);
                schedule2.setScheduleDay(scheduleDay[i]);
                schedule2.setContactMedia(schedule1.getContactMedia());


                scheduleList.add(schedule2);


            }
        }
        if (scheduleList.size()>1){
            model.addAttribute("flag",1);
        }
        else {
            model.addAttribute("flag",0);
        }
        model.addAttribute("scheduleList",scheduleList);
        //Display end

        return "doctorSchedule";
    }
    @PostMapping("/submit-schedule")
    public String postYourSchedule(Model model, DoctorsScheduleDto doctorsScheduleDto){
        doctorsSchedulingService.updateSchedule(doctorsScheduleDto);
        return "dummy";
    }
}
