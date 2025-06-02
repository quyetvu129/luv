package com.example.luv.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDtoImpl implements UserDto {
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
