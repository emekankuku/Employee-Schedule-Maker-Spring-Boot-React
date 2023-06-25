package com.example.build.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.build.Repository.DaysOffRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.dto.DaysOffDtos.daysOffDto;
import com.example.build.dto.ScheduleDtos.CreateScheduleDto;
import com.example.build.dto.ScheduleDtos.deleteScheduleDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;
import com.example.build.exceptions.IncorrectDateRangeException;
import com.example.build.model.DaysOff;
import com.example.build.model.Group;
import com.example.build.model.Schedule;

@Service
public class ScheduleService {

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
        // if(dto.getStartDate() == "" || dto.getEndDate() == "")
        //     throw new IncorrectDateRangeException("Incorrect Date Range");
        LocalDate startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dto.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
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
