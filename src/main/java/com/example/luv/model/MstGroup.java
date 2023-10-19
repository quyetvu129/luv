package com.example.luv.model;

import javax.persistence.*;

@Entity
@Table(name = "mstGroup")
public class MstGroup {
    @Id
    @Column(name = "groupId")
    private Integer groupId;

    @Column(name = "groupName")
    private String groupName;

    public Integer getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
