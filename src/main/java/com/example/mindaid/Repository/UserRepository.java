package com.example.mindaid.Repository;

import com.example.mindaid.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
    @Query(value = "SELECT * FROM users  WHERE user_email =:email", nativeQuery = true)
    public List<User> findByEmail(String email);
    @Query("SELECT u FROM User u WHERE u.resetPasswordToken = ?1")
    public User findByResetPasswordToken(String token);
    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);
}
