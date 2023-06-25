package com.example.build.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.build.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long>{
    Group findByName(String name);
    
}