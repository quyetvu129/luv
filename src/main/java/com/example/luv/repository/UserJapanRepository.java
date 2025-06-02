package com.example.luv.repository;

import com.example.luv.model.TblUser;
import com.example.luv.model.TblUserJapan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJapanRepository extends CrudRepository<TblUserJapan, Integer> {

    Optional<TblUserJapan> findByUserId(Integer id);
}
