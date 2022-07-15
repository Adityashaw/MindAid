package com.example.mindaid.Service;

import com.example.mindaid.Model.Login;
import com.example.mindaid.Model.User;
import com.example.mindaid.Repository.UserRepository;
import com.sun.istack.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.*;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EncodePassword encodePassword;

    @PersistenceContext
    public EntityManager entityManager;
    public int loginValidation(Login login) throws UnsupportedEncodingException {

        List<User> userList = userRepository.findByEmail(login.email);
        if (userList.size() > 0) {
            if (login.password.equals(userList.get(0).confirmPassword)) {
                if (userList.get(0).isEnabled()) return 1;
                else return 2;
            }
          else return 3;
            }
        else return 3;
        }
    }