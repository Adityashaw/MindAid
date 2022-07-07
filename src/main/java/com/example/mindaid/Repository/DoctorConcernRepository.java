package com.example.mindaid.Repository;

import com.example.mindaid.Model.DoctorConcern;
import com.example.mindaid.Model.Doctors;
import com.example.mindaid.Model.User;
import net.minidev.json.JSONUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorConcernRepository extends JpaRepository<DoctorConcern , Integer> {
    @Query(value = "SELECT d.* FROM concern c,doctors d, doctorconcern dc "+" WHERE dc.concern_id=:concern_id "+
            " AND dc.concern_id=c.iconcernid "+" AND dc.doc_id=d.doc_id ", nativeQuery = true)
    public List<Doctors> findByConcernId(int concern_id);
}
