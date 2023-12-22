package com.example.build.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.build.Service.ReportService;
import com.example.build.dto.ReportDto;
import com.example.build.dto.GroupDtos.GroupDto;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/report")

public class ReportController {

    @Autowired
    private ReportService reportService;

    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @PostMapping("/getReport")
    public ReportDto test5(@Valid @RequestBody GroupDto dto) {
        return reportService.getGroupReport(dto);
    }
    
}
