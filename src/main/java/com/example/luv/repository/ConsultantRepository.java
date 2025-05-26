package com.example.luv.repository;

import com.example.luv.dto.response.ConsultantDto;
import com.example.luv.model.ConsultantEntity;
import com.example.luv.model.TblUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultantRepository extends CrudRepository<ConsultantEntity, Long> {

    @Query(value = "select c.id, c.name, c.score from consultant c", nativeQuery = true)
    List<ConsultantDto> search();

    @Query(value = "select round(cast(avg(c.score) as numeric), 2) from consultant c", nativeQuery = true)
    Float getAvgScore();
}
