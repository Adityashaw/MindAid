package com.example.mindaid.Controller;

import com.example.mindaid.Dto.*;
import com.example.mindaid.Dto.Admin.AppointmentDto;
import com.example.mindaid.Model.*;
import com.example.mindaid.Repository.*;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;

@Controller
public class AdminController {
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
    MailSendingService mailSendingService;
    @Autowired
    DynamicSchedulingrepository dynamicSchedulingrepository;
    @Autowired
    SessionValidatorService sessionValidatorService;


    //admin login

    //admin login end

    @GetMapping("/admin-profile")
    public String getAdminProfile(Model model, HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<AppointmentDto> appointmentListAdmin= adminService.getAppointmentListAdmin("pending");
        List<Doctors> pendingDoctorList=new ArrayList<>();
        String status="Appointments";
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status",status);
        model.addAttribute("pendingDoctorList",pendingDoctorList);
        Payment payment=new Payment();
        model.addAttribute(payment);
        return "adminProfile";
    }
    @RequestMapping(value = "/pending-appointment/{status}", method = RequestMethod.POST)
    public String postAdminProfile(Model model, @PathVariable("status") String status, Payment payment,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Payment> paymentList=paymentRepository.findByPaymentId(payment.getPaymentId());
        paymentList.get(0).setApproval(status);
        paymentRepository.save(paymentList.get(0));
        List<AppointmentDto> appointmentListAdmin= adminService.getAppointmentListAdmin("pending");
        int notification= appointmentListAdmin.size();
        String heading="Pending Appointments";
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("notification",notification);
        model.addAttribute("status",heading);
        return "adminProfile";
    }
    @GetMapping("/admin-previous-appointments")
    public String getAdminProfilePrevious(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<AppointmentDto> appointmentListAdmin= adminService.getAppointmentListAdmin("approved");
        List<Payment> appointmentPendingListAdmin= paymentRepository.findByApprovalStatus("pending");
        int notification= appointmentPendingListAdmin.size();
        String status="Previous Appointments";
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("notification",notification);
        model.addAttribute("status",status);
        Payment payment=new Payment();
        model.addAttribute(payment);

        return "adminProfile";
    }
    @GetMapping("/new-therapist")
    public String getNewTherapist(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("pending");
        List<Integer> ButtonFlagNewTherapist=new ArrayList<>();
        List<Integer> ButtonFlagAllDoctors=new ArrayList<>();
        ButtonFlagNewTherapist.add(1);
        for (Doctors doctors:pendingDoctorList){
            doctors.setPhotos("\\assets\\user-photos\\"+doctors.getDocId()+ "\\"+doctors.getPhotos());
        }
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="New Therapist Requests";
        Doctors doctors=new Doctors();
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("flag1",ButtonFlagNewTherapist);
        model.addAttribute("flag2",ButtonFlagAllDoctors);
        model.addAttribute(doctors);
        return "adminProfile";

    }
    @PostMapping("/appointment-contact")
    public String postNewTherapist(Model model,Doctors doctors,HttpSession httpSession) throws MessagingException, UnsupportedEncodingException {
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Doctors> findDoctors=doctorsRepository.findByDocId(doctors.getDocId());
        mailSendingService.sendEmailToNewApplicant(findDoctors.get(0).getEmail(),findDoctors.get(0).getName());
        findDoctors.get(0).setApproval("contacted");
        doctorsRepository.save(findDoctors.get(0));
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("pending");
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="New Therapist Requests";
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute(doctors);
        return "adminProfile";

    }
    @GetMapping("/edit-requests")
    public String getEditRequests(Model model,HttpSession httpSession) throws NumberFormatException{
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<DoctorsScheduleDto>DoctorScheduleList=new ArrayList<>();
        List<Integer>scheduleDocIds=scheduleRepository.findByApproval("pending");
        for (int schedule:scheduleDocIds){
            List<Doctors>doctorsList=doctorsRepository.findByDocId(schedule);
            DoctorsScheduleDto doctorsScheduleDto=new DoctorsScheduleDto();
            doctorsScheduleDto.setDoctors(doctorsList.get(0));
            List<Schedule>schedules=scheduleRepository.findByDocIdAndApproval(schedule,"pending");
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
            doctorsScheduleDto.setScheduleList(scheduleList);
            DoctorScheduleList.add(doctorsScheduleDto);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("nothing");
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="Schedule Update Requests";
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("DoctorScheduleList",DoctorScheduleList);
        DoctorsScheduleDto doctorsScheduleDto2=new DoctorsScheduleDto();
        model.addAttribute("doctorsScheduleDto",doctorsScheduleDto2);
        return "adminProfile";
    }
    @PostMapping("/approve-request")
    public String postEditRequests(Model model,DoctorsScheduleDto doctorsScheduleDto1,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<DoctorConcern>doctorConcernList=doctorConcernRepository.findByDocIdAndApproval(doctorsScheduleDto1.getDocId(),"approved");
        for(DoctorConcern doctorConcern:doctorConcernList){
            doctorConcernRepository.delete(doctorConcern);
        }
        List<DoctorConcern>doctorConcernList1=doctorConcernRepository.findByDocIdAndApproval(doctorsScheduleDto1.getDocId(),"pending");
        for(DoctorConcern doctorConcern:doctorConcernList1){
            doctorConcern.setApproval("approved");
            doctorConcernRepository.save(doctorConcern);
        }
        List<Schedule>scheduleList2=scheduleRepository.findByDocIdAndApproval(doctorsScheduleDto1.getDocId(),"approved");
        for (Schedule schedule:scheduleList2){
            List<DynamicScheduling> dynamicSchedulingList= dynamicSchedulingrepository.findByScheduleId(schedule.getScheduleId());
            for(DynamicScheduling dynamicScheduling:dynamicSchedulingList){
                dynamicSchedulingrepository.delete(dynamicScheduling);
            }
            schedule.setApproval("previous");
            scheduleRepository.save(schedule);
        }
        List<Schedule>scheduleList1=scheduleRepository.findByDocIdAndApproval(doctorsScheduleDto1.getDocId(),"pending");
        for (Schedule schedule:scheduleList1){
            schedule.setApproval("approved");
            scheduleRepository.save(schedule);
        }
        List<DoctorsScheduleDto>DoctorScheduleList=new ArrayList<>();
        List<Integer>scheduleDocIds=scheduleRepository.findByApproval("pending");
        List<Schedule> scheduleList=new ArrayList<>();
        for (int schedule:scheduleDocIds){
            List<Doctors>doctorsList=doctorsRepository.findByDocId(schedule);
            DoctorsScheduleDto doctorsScheduleDto=new DoctorsScheduleDto();
            doctorsScheduleDto.setDoctors(doctorsList.get(0));
            List<Schedule>schedules=scheduleRepository.findByDocIdAndApproval(schedule,"pending");
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
            doctorsScheduleDto.setScheduleList(scheduleList);
            DoctorScheduleList.add(doctorsScheduleDto);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("nothing");
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="New Therapist Requests";
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("DoctorScheduleList",DoctorScheduleList);
        DoctorsScheduleDto doctorsScheduleDto2=new DoctorsScheduleDto();
        model.addAttribute("doctorsScheduleDto",doctorsScheduleDto2);
        return "adminProfile";
    }
    @PostMapping("/deny-request")
    public String postDenyRequest(Model model,DoctorsScheduleDto doctorsScheduleDto1,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }

        List<Schedule>scheduleList1=scheduleRepository.findByDocIdAndApproval(doctorsScheduleDto1.getDocId(),"pending");
        for (Schedule schedule:scheduleList1){
            schedule.setApproval("denied");
            scheduleRepository.save(schedule);
        }
        List<DoctorsScheduleDto>DoctorScheduleList=new ArrayList<>();
        List<Integer>scheduleDocIds=scheduleRepository.findByApproval("pending");
        for (int schedule:scheduleDocIds){
            List<Doctors>doctorsList=doctorsRepository.findByDocId(schedule);
            DoctorsScheduleDto doctorsScheduleDto=new DoctorsScheduleDto();
            doctorsScheduleDto.setDoctors(doctorsList.get(0));
            List<Schedule>schedules=scheduleRepository.findByDocIdAndApproval(schedule,"pending");
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
            doctorsScheduleDto.setScheduleList(scheduleList);
            DoctorScheduleList.add(doctorsScheduleDto);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("nothing");
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="New Therapist Requests";
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("DoctorScheduleList",DoctorScheduleList);
        DoctorsScheduleDto doctorsScheduleDto2=new DoctorsScheduleDto();
        model.addAttribute("doctorsScheduleDto",doctorsScheduleDto2);
        return "adminProfile";
    }
    @GetMapping("/all-doctors")
    public String getAllDoctors(Model model,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("contacted");
        List<Integer> ButtonFlagNewTherapist=new ArrayList<>();
        List<Integer> ButtonFlagAllDoctors=new ArrayList<>();
        ButtonFlagAllDoctors.add(1);
        for (Doctors doctors:pendingDoctorList){
            doctors.setPhotos("\\assets\\user-photos\\"+doctors.getDocId()+ "\\"+doctors.getPhotos());
        }
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="All Therapists";
        Doctors doctors=new Doctors();
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("flag1",ButtonFlagNewTherapist);
        model.addAttribute("flag2",ButtonFlagAllDoctors);
        model.addAttribute(doctors);
        return "adminProfile";
    }
    @PostMapping("/doctor-remove")
    public String removeDoctor(Model model,Doctors doctor,HttpSession httpSession){
        if ((sessionValidatorService.userSessionValidator(httpSession))) {
            return sessionValidatorService.loginPageReturn(model);
        }
        List<Doctors>doctorsList=doctorsRepository.findByDocId(doctor.getDocId());
        for (Doctors doctors:doctorsList){
            doctorsRepository.delete(doctors);
        }
        List<Schedule>scheduleList=scheduleRepository.findByDocIdAndApproval(doctor.getDocId(),"approved");
        for (Schedule schedule:scheduleList){
            scheduleRepository.delete(schedule);
        }
        List<User>userList=userRepository.findByEmail(doctorsList.get(0).getEmail());
        for (User user:userList){
            userRepository.delete(user);
        }
        List<Doctors> pendingDoctorList= doctorsRepository.findByApproval("contacted");
        List<Integer> ButtonFlagNewTherapist=new ArrayList<>();
        List<Integer> ButtonFlagAllDoctors=new ArrayList<>();
        ButtonFlagAllDoctors.add(1);
        for (Doctors doctors:pendingDoctorList){
            doctors.setPhotos("\\assets\\user-photos\\"+doctors.getDocId()+ "\\"+doctors.getPhotos());
        }
        List<AppointmentDto> appointmentListAdmin=new ArrayList<>();
        String status="All Therapists";
        Doctors doctors=new Doctors();
        int notification= pendingDoctorList.size();
        model.addAttribute("notification",notification);
        model.addAttribute("pendingDoctorList", pendingDoctorList);
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("status", status);
        model.addAttribute("flag1",ButtonFlagNewTherapist);
        model.addAttribute("flag2",ButtonFlagAllDoctors);
        model.addAttribute(doctors);
        return "adminProfile";
    }
}
