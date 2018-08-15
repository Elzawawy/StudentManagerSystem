package com.xcodesystemsinterns.studentmanager;

public class StudentDescription {
    private String name;
    private int id;
    private int grade;

    public StudentDescription(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public StudentDescription(String name, int id, int grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
