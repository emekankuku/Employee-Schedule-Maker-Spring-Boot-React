package com.example.build.Service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.build.Repository.CheckInRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.dto.CheckIn.CheckInDto;
import com.example.build.dto.CheckIn.CheckInDto2;
import com.example.build.model.CheckIn;
import com.example.build.model.Group;

@Service
public class CheckInService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public CheckInDto2 getRecentCheckIn(CheckInDto dto) { //Gets recent checkin of the day
        LocalDateTime now = LocalDateTime.now();
        String email = dto.getEmail();
        String groupName = dto.getGroup();
        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");
            
        CheckIn recent = checkInRepository.recentCheckIn(email, group.getId());
        if(recent == null || now.getMonth() != recent.getLt().getMonth() || now.getDayOfMonth() != recent.getLt().getDayOfMonth())
            return new CheckInDto2();
        return new CheckInDto2(recent);
    }

    public CheckInDto checkIn(CheckInDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        
        if(scheduleRepository.findByEmailAndGroup_id(email, group.getId()) == null)
            throw new IllegalStateException("User must have submitted a schedule before checking in or out");

        CheckIn temp = checkInRepository.recentCheckIn(email, group.getId());
        if (temp == null) {
            checkInRepository.save(new CheckIn(email, group));
            return dto;
        }
        CheckIn checkIn = new CheckIn(email, group);
        checkIn.setInOrOut(temp.getStatus() == true ? "Check Out" : "Check In");
        checkIn.setStatus(!temp.getStatus());
        checkInRepository.save(checkIn);

        return dto;
    }
}
