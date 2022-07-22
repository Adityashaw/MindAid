package com.example.mindaid.Controller;

import com.example.mindaid.Dto.*;
import com.example.mindaid.Dto.Admin.AppointmentDto;
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

    @GetMapping("/admin-profile")
    public String getAdminProfile(Model model){
        List<AppointmentDto> appointmentListAdmin= adminService.getAppointmentListAdmin("pending");
        int notification= appointmentListAdmin.size();
        String status="Appointments";
        model.addAttribute("appointmentListAdmin", appointmentListAdmin);
        model.addAttribute("notification",notification);
        model.addAttribute("status",status);
        Payment payment=new Payment();
        model.addAttribute(payment);

        return "adminProfile";
    }
    @RequestMapping(value = "/pending-appointment/{status}", method = RequestMethod.POST)
    public String postAdminProfile(Model model, @PathVariable("status") String status, Payment payment){
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
    public String getAdminProfilePrevious(Model model){
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
}
