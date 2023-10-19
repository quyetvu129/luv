package com.example.luv.model;

import javax.persistence.*;

@Entity
@Table(name = "mstJapan")
public class MstJapan {
    @Id
    @Column(name = "codeLevel")
    private String codeLevel;

    @Column(name = "nameLevel")
    private String nameLevel;

    public String getCodeLevel() {
        return this.codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public String getNameLevel() {
        return this.nameLevel;
    }

    public void setNameLevel(String nameLevel) {
        this.nameLevel = nameLevel;
    }
}
