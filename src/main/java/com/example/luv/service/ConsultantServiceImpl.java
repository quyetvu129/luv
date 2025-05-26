package com.example.luv.service;

import com.example.luv.dto.response.ConsultantDto;
import com.example.luv.repository.ConsultantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantServiceImpl implements ConsultantService{

    @Autowired
    private ConsultantRepository consultantRepository;
    @Override
    public List<ConsultantDto> search() {
        List<ConsultantDto> dtoList = consultantRepository.search();

        Float avgScore = consultantRepository.getAvgScore();
        System.out.println("avgScore= " + avgScore);

        return dtoList;
    }
}
