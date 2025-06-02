package com.example.luv.repository;

import com.example.luv.dto.response.UserDto;
import com.example.luv.model.TblUser;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<TblUser, Integer> {

    @Query(value = "SELECT tu.userId, tu.fullName, tu.birthday, mg.groupName, tu.email, tu.tel, mj.nameLevel, tuj.endDate, tuj.total  \n" +
            "FROM mstGroup mg JOIN tblUser tu ON mg.groupId = tu.groupId " +
            "JOIN tblUserJapan tuj ON tu.userId = tuj.userId " +
            "JOIN mstJapan mj ON mj.codeLevel = tuj.codeLevel " +
            "WHERE (:name IS NULL OR tu.fullName LIKE %:name%) " +
            "AND (:endDate IS NULL OR tuj.endDate > :endDate) " +
            "LIMIT :size OFFSET :page"
            , nativeQuery = true)
    List<UserDto> getListUser(String name, LocalDate endDate, int page, int size);

     Optional<TblUser>  findByUserId(Integer id);

    Optional<TblUser> findByLoginName(String loginName);

    @Query(value = "SELECT COUNT(*) FROM tblUser tu " +
            "WHERE tu.loginName = :loginName AND (:userId IS NULL OR tu.userId != :userId)", nativeQuery = true)
    int countByLoginName(String loginName, Integer userId);

    @Query(value = "SELECT COUNT(*) FROM tblUser tu " +
            "WHERE tu.email = :email AND (:userId IS NULL OR tu.userId != :userId)", nativeQuery = true)
    int countByEmail(String email, Integer userId);
}
