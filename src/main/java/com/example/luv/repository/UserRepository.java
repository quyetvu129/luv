package com.example.luv.repository;

import com.example.luv.dto.UserDto;
import com.example.luv.model.TblUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<TblUser, Integer> {

    @Query(value = "select tu.userId, tu.fullName, tu.birthday, mg.groupName, tu.email, tu.tel, mj.nameLevel, tuj.endDate, tuj.total  \n" +
            "from mstGroup mg join tblUser tu on mg.groupId = tu.groupId \n" +
            "join tblUserJapan tuj on tu.userId = tuj.userId \n" +
            "join mstJapan mj on mj.codeLevel = tuj.codeLevel", nativeQuery = true)
    List<UserDto> getListUser();
}
