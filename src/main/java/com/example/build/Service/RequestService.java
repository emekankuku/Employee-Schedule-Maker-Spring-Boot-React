package com.example.build.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.build.Repository.ApprovalRequestRepository;
import com.example.build.Repository.DaysOffRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.SchedRequestRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.Repository.UserRepository;
import com.example.build.dto.EmailDto;
import com.example.build.dto.DaysOffDtos.daysOffDto;
import com.example.build.dto.Requests.SchedRequestDto;
import com.example.build.dto.ScheduleDtos.CreateScheduleDto;
import com.example.build.dto.ScheduleDtos.deleteScheduleDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;
import com.example.build.exceptions.IncorrectDateRangeException;
import com.example.build.model.DaysOff;
import com.example.build.model.Group;
import com.example.build.model.Schedule;
import com.example.build.model.User;
import com.example.build.model.Requests.ApprovalRequest;
import com.example.build.model.Requests.SchedRequest;

@Service
public class RequestService {

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

    @Autowired
    private ApprovalRequestRepository approvalRequestRepository;

    public List<SchedRequestDto> getRequests(EmailDto dto) {
        List<SchedRequestDto> requests = new ArrayList<>();
        for (SchedRequest request : schedRequestRepository.findByReceiver(dto.getEmail())) {
            SchedRequestDto req = new SchedRequestDto(request);
            requests.add(req);
        }
        return requests;
    }

    public scheduleDto approveRequest(SchedRequestDto dto) {
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
        schedule.setFullName(dto.getName());
        scheduleRepository.save(schedule);

        SchedRequest request = schedRequestRepository.findByEmailAndGroupName(email, groupName);
        schedRequestRepository.deleteById(request.getId());

        approvalRequestRepository.save(new ApprovalRequest(email, "Your schedule has been approved."));

        return new scheduleDto(schedule);
    }

    public scheduleDto denyRequest(SchedRequestDto dto) {
        String email = dto.getEmail();
        String groupName = dto.getGroup();

        Group group = groupRepository.findByName(groupName);
        if (group == null)
            throw new IllegalStateException("Group does not exist");

        SchedRequest request = schedRequestRepository.findByEmailAndGroupName(email, groupName);
        schedRequestRepository.deleteById(request.getId());

        approvalRequestRepository.save(new ApprovalRequest(email, "Your schedule has been denied."));

        return new scheduleDto();
    }

    

}
