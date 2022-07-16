package com.example.mindaid.Repository;

import com.example.mindaid.Model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Integer> {
    @Query(value = "select  * from schedule where schedule_id=:schedule_id",nativeQuery = true)
    public List<Schedule> findByScheduleId(int schedule_id);
}
