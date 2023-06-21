package com.example.registration.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registration.Repository.DaysOffRepository;
import com.example.registration.Repository.GroupRepository;
import com.example.registration.Repository.ScheduleRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.DaysOffDtos.daysOffDto;
import com.example.registration.dto.GroupDtos.GroupDto;
import com.example.registration.dto.GroupDtos.UtoGDto;
import com.example.registration.dto.GroupDtos.addUserDto;
import com.example.registration.dto.ScheduleDtos.CreateScheduleDto;
import com.example.registration.dto.ScheduleDtos.deleteScheduleDto;
import com.example.registration.dto.ScheduleDtos.scheduleDto;
import com.example.registration.exceptions.DuplicateGroupException;
import com.example.registration.exceptions.DuplicateUserException;
import com.example.registration.exceptions.IncorrectDateRangeException;
import com.example.registration.model.DaysOff;
// import com.example.registration.model.Employee;
import com.example.registration.model.Group;
// import com.example.registration.model.Manager;
import com.example.registration.model.User;
import com.example.registration.model.Schedule;

@Service
public class ScheduleService {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

    public scheduleDto createSchedule(CreateScheduleDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        Schedule taken = scheduleRepository.findByEmailAndGroup_id(email, group.getId());
        if (taken != null)
            scheduleRepository.deleteById(taken.getId());

        Schedule schedule = new Schedule(dto);
        schedule.setGroup(group);
        scheduleRepository.save(schedule);

        return new scheduleDto(schedule);
    }

    public daysOffDto createDaysOff(daysOffDto dto) {
        LocalDate startDate = LocalDate.parse(dto.getStartYear()+"-"+dto.getStartMonth()+"-"+dto.getStartDay());
        LocalDate endDate = LocalDate.parse(dto.getEndYear()+"-"+dto.getEndMonth()+"-"+dto.getEndDay());
        if(startDate.isAfter(endDate))
            throw new IncorrectDateRangeException("Incorrect Date Range");

        String email = dto.getEmail();
        String groupName = dto.getGroup();

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        DaysOff taken = daysOffRepository.findByEmailAndGroup_id(email, group.getId());
        if (taken != null)
            daysOffRepository.deleteById(taken.getId());

        DaysOff daysOff = new DaysOff(dto);
        daysOff.setGroup(group);
        daysOffRepository.save(daysOff);

        return new daysOffDto(daysOff);
    }

    public String deleteSchedule(deleteScheduleDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();
        Group group = groupRepository.findByName(dto.getGroup());
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        Schedule schedule = scheduleRepository.findByEmailAndGroup_id(email, group.getId());
        if (schedule == null)
            throw new IllegalStateException("Schedule does not exist");

        scheduleRepository.deleteById(schedule.getId());

        return "Schedule of " + email + " has been removed from group: " + groupName;
    }
    
    public String deleteDaysOff(deleteScheduleDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();
        Group group = groupRepository.findByName(dto.getGroup());
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        DaysOff daysOff = daysOffRepository.findByEmailAndGroup_id(email, group.getId());
        if (daysOff == null)
            throw new IllegalStateException(email + " has no days off");

        daysOffRepository.deleteById(daysOff.getId());

        return email + "'s days off have been removed from group: " + groupName;
    }

}
