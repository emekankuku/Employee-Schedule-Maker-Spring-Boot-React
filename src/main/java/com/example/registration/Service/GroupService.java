package com.example.registration.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.registration.Repository.DaysOffRepository;
import com.example.registration.Repository.GroupRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.DaysOffDtos.DaysOffOutputDto;
import com.example.registration.dto.GroupDtos.GroupDto;
import com.example.registration.dto.GroupDtos.UtoGDto;
import com.example.registration.dto.GroupDtos.addUserDto;
import com.example.registration.dto.ScheduleDtos.scheduleDto;
import com.example.registration.exceptions.DuplicateGroupException;
import com.example.registration.exceptions.DuplicateUserException;
import com.example.registration.model.DaysOff;
// import com.example.registration.model.Employee;
import com.example.registration.model.Group;
// import com.example.registration.model.Manager;
import com.example.registration.model.User;

import com.example.registration.model.Schedule;
import java.util.Calendar;

@Service
public class GroupService {

    @Autowired
    private UserRepository<User> userRepository;

    // @Autowired
    // private UserRepository<Manager> managerRepository;

    // @Autowired
    // private UserRepository<Employee> employeeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

    public GroupDto createGroup(UtoGDto dto) {
        if (groupRepository.findByName(dto.getGroupName()) != null)
            throw new DuplicateGroupException("Group Name Taken");
        Group group = new Group(dto.getGroupName());
        User user = userRepository.findByEmail(dto.getUserEmail());
        if (!user.getRole().equals("Manager"))
            throw new DuplicateUserException("Unauthorized");
        group.addUser(user);
        return new GroupDto(groupRepository.save(group));
    }

    public GroupDto addUser(addUserDto dto) {
        Group group = groupRepository.findByName(dto.getGroupName());
        User manager = userRepository.findByEmail(dto.getManagerEmail());
        User employee = userRepository.findByEmail(dto.getEmployeeEmail());
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        if (!manager.getRole().equals("Manager"))
            throw new DuplicateUserException("Unauthorized");
        if (employee == null)
            throw new IllegalStateException("User does not exist");
        group.addUser(employee);
        groupRepository.save(group);
        return new GroupDto(group);
    }

    public List<GroupDto> getGroups(UserDto dto) {
        User user = userRepository.findByEmail((dto.getEmail()));
        if (user == null)
            throw new IllegalStateException("User does not exist");
        List<GroupDto> list = new ArrayList<>();
        for (Group group : user.getGroups())
            list.add(new GroupDto(group));
        return list;
    }

    public List<UserDto> getUsers(GroupDto dto) {
        Group group = groupRepository.findByName(dto.getName());
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        List<UserDto> list = new ArrayList<>();
        for (User user : group.getUsers())
            list.add(new UserDto(user));
        return list;
    }

    public List<scheduleDto> getSchedules(GroupDto dto) {
        Group group = groupRepository.findByName(dto.getName());
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        List<scheduleDto> schedules = new ArrayList<>();
        for (Schedule schedule : group.getSchedules()) {
            DaysOff daysOff = daysOffRepository.findByEmailAndGroup_id(schedule.getName(), group.getId());
            if (daysOff != null)
                schedule = daysOffSchedule(daysOff, schedule);
            scheduleDto scheduleDto = new scheduleDto(schedule);
            scheduleDto.setEmail(schedule.getName());
            schedules.add(scheduleDto);
        }

        return schedules;
    }

    public List<DaysOffOutputDto> getDaysOff(GroupDto dto) {
        Group group = groupRepository.findByName(dto.getName());
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        List<DaysOffOutputDto> daysOffList = new ArrayList<>();
        for (DaysOff daysOff : group.getDaysOff()) {
            DaysOffOutputDto daysOffDto = new DaysOffOutputDto(daysOff);
            daysOffDto.setEmail(daysOff.getEmail());
            daysOffList.add(daysOffDto);
        }

        return daysOffList;
    }

    // Deletes group by name
    public String deleteGroup(GroupDto dto) {
        Group group = groupRepository.findByName(dto.getName());
        if (group == null)
            throw new IllegalStateException("Group does not exist");
        groupRepository.deleteById(group.getId());
        return dto.getName() + " has been deleted";

    }

    public Schedule daysOffSchedule(DaysOff daysOff, Schedule schedule) {
        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        LocalDate start = daysOff.getStartDate(), end = daysOff.getEndDate();
        String message = "Day off";

        Calendar todayCal = Calendar.getInstance(Locale.US), startCal = Calendar.getInstance(Locale.US),
                endCal = Calendar.getInstance(Locale.US);
        todayCal.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth());
        startCal.set(start.getYear(), start.getMonthValue(), start.getDayOfMonth());
        endCal.set(end.getYear(), end.getMonthValue(), end.getDayOfMonth());
        int thisWeek = todayCal.get(Calendar.WEEK_OF_YEAR), startWeek = startCal.get(Calendar.WEEK_OF_YEAR),
                endWeek = endCal.get(Calendar.WEEK_OF_YEAR);

        if (thisWeek >= startWeek && thisWeek <= endWeek) {
            for (int i = 1; i <= 7; i++) {
                LocalDate date = LocalDate.now().with(fieldUS, i);
                if ((date.isAfter(start) || date.equals(start)) && (date.isBefore(end) || date.equals(end))) {
                    switch (i) {
                        case 1:
                            schedule.setSunday(message);
                            break;
                        case 2:
                            schedule.setMonday(message);
                            break;
                        case 3:
                            schedule.setTuesday(message);
                            break;
                        case 4:
                            schedule.setWednesday(message);
                            break;
                        case 5:
                            schedule.setThursday(message);
                            break;
                        case 6:
                            schedule.setFriday(message);
                            break;
                        case 7:
                            schedule.setSaturday(message);
                            break;
                    }
                }

            }

        }
        return schedule;
    }

}
