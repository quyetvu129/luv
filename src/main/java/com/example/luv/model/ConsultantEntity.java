package com.example.luv.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
public class ConsultantEntity {
    @Id
    private Long id;
    private String name;
    private Float score;
}
