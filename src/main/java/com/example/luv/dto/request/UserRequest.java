package com.example.luv.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDate;
@Data
public class UserRequest {
    private String loginName;
    private Integer userId;
    private String fullName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Timestamp birthday;
    private Integer groupId;
    private String email;
    private String tel;
    private String codeLevel;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Timestamp endDate;
    private Integer total;
}
