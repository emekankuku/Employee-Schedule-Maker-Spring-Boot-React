package com.example.build.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.build.model.CheckIn;
import com.example.build.model.Requests.SchedRequest;

import jakarta.transaction.Transactional;

@Repository
public interface SchedRequestRepository extends JpaRepository<SchedRequest, Long> {
    List<SchedRequest> findByReceiver(String receiver);

    SchedRequest findByEmailAndGroupName(String sender, String group);
}
