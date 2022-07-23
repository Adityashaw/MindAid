package com.example.mindaid.Repository;

import com.example.mindaid.Model.Doctors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors, Integer> {
    @Query(value = "select * from doctors where doc_id=:doc_id",nativeQuery = true)
    public List<Doctors>findByDocId(int doc_id);

    @Query(value = "select * from doctors where approval=:approval",nativeQuery = true)
    public List<Doctors>findByApproval(String approval);
}
