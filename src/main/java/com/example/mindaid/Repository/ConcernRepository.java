package com.example.mindaid.Repository;

import com.example.mindaid.Model.Concern;
import com.example.mindaid.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ConcernRepository extends JpaRepository<Concern, Integer> {
}
