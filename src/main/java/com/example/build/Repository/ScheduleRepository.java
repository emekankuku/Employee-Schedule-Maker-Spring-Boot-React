package com.example.build.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.build.model.Schedule;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
    Schedule findByEmailAndGroup_id(String name, long group_id);

}
