package com.example.build.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.build.Service.CheckInService;
import com.example.build.Service.RequestService;
import com.example.build.dto.EmailDto;
import com.example.build.dto.CheckIn.CheckInDto;
import com.example.build.dto.CheckIn.CheckInDto2;
import com.example.build.dto.Requests.SchedRequestDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;

    public RequestController(RequestService requestService){
        this.requestService = requestService;
    }

    @PostMapping("/schedules")
    public List<SchedRequestDto> checkIn(@Valid @RequestBody EmailDto dto) {
        return requestService.getRequests(dto);
    }

    @PostMapping("/approve")
    public scheduleDto approveRequest(@Valid @RequestBody SchedRequestDto dto) {
        return requestService.approveRequest(dto);
    }

    @PostMapping("/deny")
    public scheduleDto denyRequest(@Valid @RequestBody SchedRequestDto dto) {
        return requestService.denyRequest(dto);
    }
    
}
