package com.example.luv.dto;

import org.apache.tomcat.jni.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public interface UserDto {
    Integer getUserId();
    String getFullName();
    LocalDate getBirthday();
    String getGroupName();
    String getEmail();
    String getTel();
    String getNameLevel();
    LocalDate getEndDate();
    Integer getTotal();

}
