package com.example.luv.service;

import com.example.luv.dto.request.UserRequest;
import com.example.luv.dto.response.UserDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<UserDto> searchUser(String name, LocalDate endDate, int page, int size);

    UserDto searchDetailUser(Integer id);

    void createUser(UserRequest userRequest);

    void updateUser(UserRequest userRequest, Integer id);

    void deleteUser(Integer id);
}
