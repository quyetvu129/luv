package com.example.luv.service;

import com.example.luv.dto.response.ConsultantDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ConsultantService {

    List<ConsultantDto> search();

}
