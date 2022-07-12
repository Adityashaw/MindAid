package com.example.mindaid.Repository;

import com.example.mindaid.Model.DoctorConcern;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Model.User;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorConcernRepository extends JpaRepository<DoctorConcern , Integer> {
    @Query(value = "SELECT distinct d.*,s.schedule_day,s.schedule_time_start,s.fee,s.contact_media FROM concern c,doctors d, doctorconcern dc, schedule s"+
            " WHERE dc.concern_id IN :concerns"+
            " AND dc.concern_id=c.iconcernid "+
            " AND dc.doc_id=d.doc_id "+
            " AND d.doc_id = s.doc_id "+
            " AND dc.doc_id=s.doc_id "+
            " AND s.contact_media=:contactMedia "+
            " ORDER BY d.name ASC ",
            nativeQuery = true)
    public List<Object[]> findByConcernId( int[] concerns,String contactMedia);
}
