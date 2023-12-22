package com.example.build.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.build.Service.CheckInService;
import com.example.build.dto.CheckIn.CheckInDto;
import com.example.build.dto.CheckIn.CheckInDto2;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/checkIn")
public class CheckInController {
    
    @Autowired
    private CheckInService checkInService;

    public CheckInController(CheckInService checkInService){
        this.checkInService = checkInService;
    }

    @PostMapping("/checkIn")
    public CheckInDto checkIn(@Valid @RequestBody CheckInDto dto) {
        return checkInService.checkIn(dto);
    }

    @PostMapping("/recentCheckIn")
    public CheckInDto2 getRecentCheckIn(@Valid @RequestBody CheckInDto dto) {
        return checkInService.getRecentCheckIn(dto);
    }
}
