package com.example.build.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.build.model.CheckIn;

@Repository
public interface CheckInRepository extends JpaRepository<CheckIn, Long>{
    List<CheckIn> findByEmailAndGroup_id(String name, long group_id);

    @Query(value = "SELECT * FROM check_in WHERE group_id=:groupId AND lt > :localDate ORDER BY lt", nativeQuery = true)
    List<CheckIn> findByGroup_id(@Param("groupId") Long groupId, @Param("localDate") LocalDateTime localDate);

    @Query(value = "SELECT DISTINCT email FROM check_in WHERE group_id=:groupId", nativeQuery = true)
    List<String> getEmailsByGroup_id(@Param("groupId") Long groupId);

    @Query(value = "SELECT * FROM check_in WHERE email=:email AND group_id=:groupId ORDER BY lt DESC LIMIT 1", nativeQuery = true)
    CheckIn recentCheckIn(@Param("email") String email, @Param("groupId") Long groupId);
}
