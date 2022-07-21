package com.example.mindaid.Controller;
import com.example.mindaid.Dto.DoctorsDto;
import com.example.mindaid.Dto.PaymentDto;
import com.example.mindaid.Model.Payment;
import com.example.mindaid.Repository.*;
import com.example.mindaid.Request.Signup_request;
import com.example.mindaid.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Random;

@Controller
public class PaymentController {
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

    @GetMapping("/booking")
    public String getBooking(Model model){
        return "payment";
    }

    @GetMapping("/booking-confirm")
    public String getConfirm(){
        return "paymentConfirm";
    }
    @PostMapping("/booking-confirm")
    public String postBooking(Model model, Payment paymentDto, DoctorsDto doctorsDto){

        schedulingService.savePaymentDetails(paymentDto,doctorsDto);
        schedulingService.updateScheduleTimeAvailibility(doctorsDto,paymentDto);
        model.addAttribute(paymentDto);
        return "paymentConfirm";
    }
}
