package com.example.luv.controller;

import com.example.luv.dto.response.ConsultantDto;
import com.example.luv.dto.response.UserDto;
import com.example.luv.service.ConsultantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsultantController {

    @Autowired
    private ConsultantService consultantService;

    @GetMapping("/search")
    public List<ConsultantDto> search() {
        return consultantService.search();
    }
}
