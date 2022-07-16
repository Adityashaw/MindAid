package com.example.mindaid.Repository;

import com.example.mindaid.Model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    @Query(value = "select * from payment where user_id=:user_id",nativeQuery = true)
    public List<Payment> findByUserId(int user_id);
}
