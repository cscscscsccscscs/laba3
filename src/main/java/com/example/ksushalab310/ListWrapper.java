package com.example.ksushalab310;

import java.awt.*;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "groups")
public class ListWrapper {

    private List<Groups> groups;

    @XmlElement(name = "group")
    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> teachers) {
        this.groups = teachers;
    }
}
