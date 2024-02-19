package com.example.luv.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Setter
@Getter
public class UserInput {
    private String loginName;
    private Integer userId;
    private String fullName;
    private LocalDate birthday;
    private String groupName;
    private String email;
    private String tel;
    private String nameLevel;
    private LocalDate endDate;
    private Integer total;


}
