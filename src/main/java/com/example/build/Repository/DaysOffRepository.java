package com.example.build.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.build.model.CheckIn;
import com.example.build.model.DaysOff;
import com.example.build.model.Schedule;

@Repository
public interface DaysOffRepository extends JpaRepository<DaysOff, Long>{

    @Query(value = "SELECT * FROM days_off WHERE group_id=:groupId AND email=:email ORDER BY start_date", nativeQuery = true)
    List<DaysOff> findByEmailAndGroup_id(@Param("email") String email, @Param("groupId") Long groupId);

    List<DaysOff> findByGroup_id(long group_id);

    @Query(value = "SELECT * FROM days_off WHERE group_id=:groupId AND end_date > :localDate ORDER BY start_date", nativeQuery = true)
    List<DaysOff> findByGroup_id_Ordered(@Param("groupId") Long groupId, @Param("localDate") LocalDate localDate);

}
