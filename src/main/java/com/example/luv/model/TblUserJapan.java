package com.example.luv.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tblUserJapan")
public class TblUserJapan {
    @Id
    @Column(name = "dtlUserJpId")
    private Integer dtlUserJpId;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "codeLevel")
    private String codeLevel;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "total")
    private Integer total;

    public Integer getDtlUserJpId() {
        return this.dtlUserJpId;
    }

    public void setDtlUserJpId(Integer dtlUserJpId) {
        this.dtlUserJpId = dtlUserJpId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCodeLevel() {
        return this.codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
