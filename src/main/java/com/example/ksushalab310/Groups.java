package com.example.ksushalab310;

public class Groups {
    String id, faculty, group, course, count_stud, count_group;

    public Groups(String id, String faculty, String group, String course, String count_stud, String count_group) {
        this.id = id;
        this.faculty = faculty;
        this.group = group;
        this.course = course;
        this.count_stud = count_stud;
        this.count_group = count_group;
    }

    public Groups(){

    }

    public String getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getGroup() {
        return group;
    }

    public String getCourse() {
        return course;
    }

    public String getCount_stud() {
        return count_stud;
    }

    public String getCount_group() {
        return count_group;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setCount_stud(String count_stud) {
        this.count_stud = count_stud;
    }

    public void setCount_group(String count_group) {
        this.count_group = count_group;
    }
}
