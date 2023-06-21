package com.example.registration.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.registration.model.DaysOff;
import com.example.registration.model.Schedule;

@Repository
public interface DaysOffRepository extends JpaRepository<DaysOff, Long>{
    DaysOff findByEmailAndGroup_id(String name, long group_id);

}
