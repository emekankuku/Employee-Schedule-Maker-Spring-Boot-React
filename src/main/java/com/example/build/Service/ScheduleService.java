package com.example.build.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.build.Repository.DaysOffRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.SchedRequestRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.Repository.UserRepository;
import com.example.build.dto.EmailDto;
import com.example.build.dto.CheckIn.CheckInDto;
import com.example.build.dto.DaysOffDtos.daysOffDto;
import com.example.build.dto.ScheduleDtos.CreateScheduleDto;
import com.example.build.dto.ScheduleDtos.SchedOutput;
import com.example.build.dto.ScheduleDtos.deleteScheduleDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;
import com.example.build.exceptions.IncorrectDateRangeException;
import com.example.build.model.DaysOff;
import com.example.build.model.Group;
import com.example.build.model.Schedule;
import com.example.build.model.User;

@Service
public class ScheduleService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private SchedRequestRepository schedRequestRepository;

    public scheduleDto createSchedule(CreateScheduleDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();

        User user = userRepository.findByEmail(email);

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        Schedule taken = scheduleRepository.findByEmailAndGroup_id(email, group.getId());
        if (taken != null)
            scheduleRepository.deleteById(taken.getId());

        Schedule schedule = new Schedule(dto);
        schedule.setGroup(group);
        schedule.setFullName(user.getFullName());
        scheduleRepository.save(schedule);

        // request = schedRequestRepository.findByEmailAndGroupName(email, groupName);
        // if(request != null)
        // throw new IllegalStateException("You can only send one schedule request at a
        // time");
        // request = new SchedRequest(dto);
        // request.setReceiver(group.getLeader());
        // request.setGroupName(groupName);
        // request.setFullName(user.getFullName());
        // schedRequestRepository.save(request);

        return new scheduleDto(schedule);
    }

    public scheduleDto getSchedule(CheckInDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        Schedule schedule = scheduleRepository.findByEmailAndGroup_id(email, group.getId());

        if (schedule == null)
            return new scheduleDto();

        return new scheduleDto(schedule);
    }

    public daysOffDto createDaysOff(daysOffDto dto) {
        LocalDate startDate = LocalDate.parse(dto.getStart(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(dto.getEnd(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        if (startDate.isAfter(endDate))
            throw new IncorrectDateRangeException("Incorrect Date Range");

        String email = dto.getEmail();
        String groupName = dto.getGroup();

        User user = userRepository.findByEmail(email);

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        // DaysOff taken = daysOffRepository.findByEmailAndGroup_id(email,
        // group.getId());
        // if (taken != null)
        // daysOffRepository.deleteById(taken.getId());

        DaysOff daysOff = new DaysOff(dto);
        daysOff.setGroup(group);
        daysOff.setFullName(user.getFullName());
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

    // public String deleteDaysOff(deleteScheduleDto dto) {
    // String email = dto.getEmail();
    // String groupName = dto.getGroup();
    // Group group = groupRepository.findByName(dto.getGroup());
    // if (group == null)
    // throw new IllegalStateException("Group does not exist");

    // DaysOff daysOff = daysOffRepository.findByEmailAndGroup_id(email,
    // group.getId());
    // if (daysOff == null)
    // throw new IllegalStateException(email + " has no days off");

    // daysOffRepository.deleteById(daysOff.getId());

    // return email + "'s days off have been removed from group: " + groupName;
    // }

}
