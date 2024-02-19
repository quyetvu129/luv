package com.example.luv.service;

import com.example.luv.dto.UserInput;
import com.example.luv.exception.BusinessException;
import com.example.luv.model.TblUser;
import com.example.luv.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class UserUpdateService {
    private final String LOGIN_REGEX = "^[a-zA-Z0-9]*$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Autowired
    UserRepository userRepository;

    public TblUser updateUserService(Integer id, UserInput userDto) {
        TblUser userUpdate = userRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("Not exists user"));

        userUpdate.setLoginName(userDto.getLoginName());
        userUpdate.setBirthday(userDto.getBirthday());
        userUpdate.setEmail(userDto.getEmail());

        // validate loginName
        String loginName = userUpdate.getLoginName();
        if (loginName.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name is Require!");
        }
        if (loginName.length() < 4 || loginName.length() > 15) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name has length not in 4 - 15 characters");
        }
        if (!loginName.matches(LOGIN_REGEX)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name has format incorrect");
        }

        // validate birthday
        if (!isValidDate(userUpdate.getBirthday())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Format date is incorrect (Please enter: 'yyyy-mm-dd')");
        }

        // validate email
        validateEmail(userUpdate.getEmail());


        // update UserTbl
        userRepository.save(userUpdate);

        return userUpdate;
    }

    private boolean isValidDate(LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        try {

            formatter.parse(date.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email is Require!");
        }

        if (email.length() > 255) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email has length is not more than 255 characters");
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email has format incorrect");
        }
    }
}
