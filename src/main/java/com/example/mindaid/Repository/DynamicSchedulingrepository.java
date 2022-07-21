package com.example.mindaid.Repository;

import com.example.mindaid.Model.DynamicScheduling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicSchedulingrepository extends JpaRepository<DynamicScheduling,Integer> {
    @Query(value = "SELECT * from dynamic_scheduling where schedule_id=:schedule_id",nativeQuery = true)
    public List<DynamicScheduling>findByScheduleId(int schedule_id);
}
