package com.example.mindaid.Service;

import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.UserRepository;
import com.example.mindaid.Service.ExceptionHandlingService.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForgotPasswordService {
    @Autowired
    private UserRepository userRepository;

    public void updateResetPasswordToken(String token, String email) throws UserNotFoundException {
        List<User> userList = userRepository.findByEmail(email);
        if (userList.size()>0) {
            userList.get(0).setResetPasswordToken(token);
            userRepository.save(userList.get(0));
        } else {
            throw new UserNotFoundException("Could not find any user with the email " + email);
        }
    }

    public User getByResetPasswordToken(String token) {
        return userRepository.findByResetPasswordToken(token);
    }

    public void updatePassword(User user, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        user.setResetPasswordToken(null);
        userRepository.save(user);
    }
}
