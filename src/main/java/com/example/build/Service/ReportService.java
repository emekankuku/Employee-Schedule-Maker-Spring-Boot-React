package com.example.build.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import com.example.build.Repository.CheckInRepository;
import com.example.build.Repository.DaysOffRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.Repository.UserRepository;
import com.example.build.dto.ReportDto;
import com.example.build.dto.UserDto;
import com.example.build.dto.CheckIn.CheckInDto2;
import com.example.build.dto.DaysOffDtos.DaysOffOutputDto;
import com.example.build.dto.GroupDtos.CreateGroupDto;
import com.example.build.dto.GroupDtos.GroupDto;
import com.example.build.dto.GroupDtos.addUserDto;
import com.example.build.dto.ScheduleDtos.SchedOutput;
import com.example.build.dto.ScheduleDtos.scheduleDto;
import com.example.build.exceptions.DuplicateGroupException;
import com.example.build.exceptions.DuplicateUserException;
import com.example.build.model.CheckIn;
import com.example.build.model.DaysOff;
import com.example.build.model.Group;
import com.example.build.model.Report;
import com.example.build.model.Schedule;
import com.example.build.model.User;

import java.util.Calendar;
import java.util.Collections;

@Service
public class ReportService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private CheckInRepository checkInRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

    private final long pastDays = 14;

    public ReportDto getGroupReport(GroupDto dto) {
        String groupName = dto.getName();
        Group group = groupRepository.findByName(groupName);

        List<CheckIn> checkIns = checkInRepository.findByGroup_id(group.getId(),
                LocalDateTime.now().minusDays(pastDays));
         List<DaysOff> daysOff = daysOffRepository.findByGroup_id_Ordered(group.getId(),
                LocalDate.now().minusDays(pastDays));        
        List<String> groupEmails = new ArrayList<>();
        for (User user : group.getUsers())
            groupEmails.add(user.getEmail());
            
        List<Schedule> schedules = scheduleRepository.findByGroup_id(group.getId());

        Report report = new Report(checkIns, groupEmails, schedules, daysOff, pastDays);

        return new ReportDto(report);
    }

}
