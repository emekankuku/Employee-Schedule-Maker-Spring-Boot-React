package com.example.build.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.build.model.DaysOff;

@Repository
public interface DaysOffRepository extends JpaRepository<DaysOff, Long>{
    DaysOff findByEmailAndGroup_id(String name, long group_id);

}
