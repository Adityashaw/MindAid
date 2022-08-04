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

    @Query(value = "select  * from schedule where doc_id=:DocId and approval=:approval",nativeQuery = true)
    public List<Schedule> findByDocIdAndApproval(int DocId,String approval);
    @Query(value = "select  * from schedule where doc_id=:DocId and approval=:approval and contact_media=:contact_media",nativeQuery = true)
    public List<Schedule> findByDocIdAndApprovalAndMedia(int DocId,String approval,String contact_media);
    @Query(value = "select  distinct doc_id from schedule where approval=:approval",nativeQuery = true)
    public List<Integer> findByApproval(String approval);
    @Query(value = "select  distinct doc_id from schedule where approval=:approval and contact_media=:contact_media",nativeQuery = true)
    public List<Integer> findByApprovalAndMedia(String approval,String contact_media);
}
