package com.example.luv.service;

import com.example.luv.dto.request.UserRequest;
import com.example.luv.dto.response.UserDto;
import com.example.luv.dto.response.UserDtoImpl;
import com.example.luv.exception.BusinessException;
import com.example.luv.model.TblUser;
import com.example.luv.model.TblUserJapan;
import com.example.luv.repository.UserJapanRepository;
import com.example.luv.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final String LOGIN_REGEX = "^[a-zA-Z0-9]*$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserJapanRepository userJapanRepository;

    @Override
    public List<UserDto> searchUser(String name, LocalDate endDate, int page, int size) {
        List<UserDto> userList = userRepository.getListUser(name, endDate, page, size);
        return userList;
    }

    @Override
    public UserDto searchDetailUser(Integer id) {
        TblUser userUpdate = userRepository.findByUserId(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"User doesn't exists"));

        return convertEntityToDto(userUpdate);
    }

    @Transactional
    @Override
    public void createUser(UserRequest userRequest) {
        TblUser user = new TblUser();
        validateUser(userRequest, null);
        BeanUtils.copyProperties(userRequest, user);

        Integer id = userRepository.save(user).getUserId();

        TblUserJapan tblUserJapan = new TblUserJapan();
        tblUserJapan.setUserId(id);
        tblUserJapan.setCodeLevel(userRequest.getCodeLevel());
        tblUserJapan.setEndDate(userRequest.getEndDate());
        tblUserJapan.setTotal(userRequest.getTotal());

        userJapanRepository.save(tblUserJapan);
    }

    @Override
    public void updateUser(UserRequest userRequest, Integer id) {
        TblUser user = userRepository.findByUserId(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"User doesn't exists"));

        validateUser(userRequest, id);
        BeanUtils.copyProperties(userRequest, user);
        user.setUserId(id);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void deleteUser(Integer id) {
        TblUser user = userRepository.findByUserId(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND,"User doesn't exists"));

        userRepository.delete(user);

        Optional<TblUserJapan> userJapan = userJapanRepository.findByUserId(id);
        userJapan.ifPresent(tblUserJapan -> userJapanRepository.delete(tblUserJapan));
    }

    private void validateUser(UserRequest userRequest, Integer id) {

        // validate loginName
        String loginName = userRequest.getLoginName();
        if (loginName.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name is Require!");
        }
        if (loginName.length() < 4 || loginName.length() > 15) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name has length not in 4 - 15 characters");
        }
        if (!loginName.matches(LOGIN_REGEX)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name has format incorrect");
        }
        if (userRepository.countByLoginName(loginName, id) > 0) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Login name is existed!");
        }

        // validate birthday
        if (!isValidDate(userRequest.getBirthday())) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Format date is incorrect (Please enter: 'dd/MM/yyyy')");
        }

        // validate email
        validateEmail(userRequest.getEmail(), id);
    }

    private boolean isValidDate(Timestamp input) {
        if (input == null) {
            return false; // Kiểm tra null
        }

        // Chuyển đổi Timestamp thành LocalDate
        LocalDate date = input.toLocalDateTime().toLocalDate();

        // Tạo định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi LocalDate thành chuỗi
        String dateStr = date.format(formatter);

        // So sánh lại với định dạng
        try {
            LocalDate.parse(dateStr, formatter); // Kiểm tra định dạng
            return true; // Nếu không có ngoại lệ, ngày hợp lệ
        } catch (DateTimeParseException e) {
            return false; // Nếu có lỗi trong việc phân tích, trả về false
        }
    }

    private void validateEmail(String email, Integer userId) {
        if (email == null || email.isEmpty()) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email is Require!");
        }

        if (email.length() > 255) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email has length is not more than 255 characters");
        }

        if (!email.matches(EMAIL_REGEX)) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email has format incorrect");
        }
        if (userRepository.countByEmail(email, userId) > 0) {
            throw new BusinessException(HttpStatus.BAD_REQUEST, "Email is existed!");
        }
    }

    private UserDtoImpl convertEntityToDto(TblUser tblUser) {
        UserDtoImpl userDto = new UserDtoImpl();
        BeanUtils.copyProperties(tblUser, userDto);

        return userDto;
    }

}
