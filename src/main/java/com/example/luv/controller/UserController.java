package com.example.luv.controller;

import com.example.luv.dto.UserDto;
import com.example.luv.dto.UserInput;
import com.example.luv.model.TblUser;
import com.example.luv.repository.UserRepository;
import com.example.luv.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUpdateService userUpdateService;

    @GetMapping("/employees")
    public List<UserDto> getListUser() {
        return userRepository.getListUser();
    }

    @PutMapping("{id}/edit")
    public TblUser updateUser(@PathVariable Integer id, @RequestBody UserInput userDto) {


        return userUpdateService.updateUserService(id, userDto);
    }
}
