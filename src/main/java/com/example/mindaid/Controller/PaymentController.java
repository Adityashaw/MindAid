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

<<<<<<< HEAD
import java.nio.charset.Charset;
=======
>>>>>>> 85c55f6583633448e8969b924504f7029e5caf6d
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
        model.addAttribute(paymentDto);
<<<<<<< HEAD
=======
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        generatedString="https://meet.jit.si/mindaid"+generatedString;
        paymentDto.setSessionLink(generatedString);
        paymentDto.setUserId(temporaryObjectHoldService.getUserDto().getUserId());
        paymentDto.setDocId((doctorsDto.getDoc_id()));
        paymentDto.setScheduleDate(doctorsDto.getSelectedScheduleDay());
        paymentDto.setScheduleTime(doctorsDto.getSelectedScheduleTime());
        paymentRepository.save(paymentDto);
        return "paymentConfirm";
>>>>>>> 85c55f6583633448e8969b924504f7029e5caf6d

        return "paymentConfirm";
    }
}
