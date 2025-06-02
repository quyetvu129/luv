package com.example.luv.controller;

import com.example.luv.dto.request.UserRequest;
import com.example.luv.dto.response.CustomResponseEntity;
import com.example.luv.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/employee")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/search")
    public CustomResponseEntity getListUser(@RequestParam(required = false) String name,
                                            @RequestParam(required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") LocalDate endDate,
                                            @RequestParam int page,
                                            @RequestParam int size) {
        return CustomResponseEntity.ok(userService.searchUser(name, endDate, page, size));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/{id}")
    public CustomResponseEntity getDetailUser(@PathVariable Integer id) {
        return CustomResponseEntity.ok(userService.searchDetailUser(id));
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping("")
    public CustomResponseEntity createUser(@RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
        return CustomResponseEntity.success();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping("/{id}")
    public CustomResponseEntity updateUser(@PathVariable Integer id,
                                           @RequestBody UserRequest userRequest) {
        userService.updateUser(userRequest, id);
        return CustomResponseEntity.success();
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @DeleteMapping("/{id}")
    public CustomResponseEntity updateUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return CustomResponseEntity.success();
    }
}
