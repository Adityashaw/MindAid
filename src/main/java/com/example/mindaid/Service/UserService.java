package com.example.mindaid.Service;

import com.example.mindaid.Dto.UserDto;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Model.Login;
import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.DoctorsRepository;
import com.example.mindaid.Repository.UserRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EncodePassword encodePassword;
    @Autowired
    TemporaryObjectHoldService temporaryObjectHoldService;
    @Autowired
    DoctorsRepository doctorsRepository;

    @PersistenceContext
    public EntityManager entityManager;
    public int loginValidationAndUserIdTransfer(Login login, Model model, HttpSession session) throws UnsupportedEncodingException {
        List<User> userList = userRepository.findByEmail(login.email);
        List<Doctors>doctorsList=doctorsRepository.findByEmailPassword(login.email,login.password);
        if (userList.size() > 0) {
            if (login.password.equals(userList.get(0).confirmPassword)) {
                if (userList.get(0).isEnabled()) {
                    UserDto userDto=new UserDto();
                    userDto.setUserId(userList.get(0).userId);
                    userDto.setUserType(userList.get(0).getUserType());
                    userDto.setName(userList.get(0).getName());
                    if (userList.get(0).getUserType().equals("doctor")){
                        userDto.setUserId(doctorsList.get(0).getDocId());
                    }
                    temporaryObjectHoldService.setUserDto(userDto);
                    userDto.setUserDto(userDto);
                    model.addAttribute(userDto);
                    List<String>userinfos=new ArrayList<>();
                    Collections.addAll(userinfos,String.valueOf(userDto.getUserId()),userDto.getName(),userDto.getUserType());
                    session.setAttribute("userInfo",userinfos);
                    return 1;
                }
                else return 2;
            }
          else return 3;
            }
        else {
            List<Doctors> doctorList=doctorsRepository.findByEmailPassword(login.email, login.password);
            if(doctorList.size()!=0){
                User user=new User();
                user.setEmail(doctorList.get(0).getEmail());
                user.setPassword(doctorList.get(0).getLoginPassword());
                user.setUserType("doctor");
                user.setConfirmPassword(doctorList.get(0).getLoginPassword());
                user.setEnabled(true);
                user.setPhone(doctorList.get(0).getMobile());
                user.setName(doctorList.get(0).getName());
                userRepository.save(user);
                UserDto userDto=new UserDto();
                userDto.setUserId(doctorList.get(0).getDocId());
                userDto.setName(doctorList.get(0).getName());
                userDto.setUserType("doctor");
                temporaryObjectHoldService.setUserDto(userDto);
                List<String>userinfos=new ArrayList<>();
                Collections.addAll(userinfos,String.valueOf(userDto.getUserId()),userDto.getName(),userDto.getUserType());
                session.setAttribute("userInfo",userinfos);
                return 1;
            }
            return 3;
        }
        }
        }
