package com.xcodesystemsinterns.studentmanager;

public class Assignment {
    private int ID;
    private String name;
    private String className;
    private String date;
    private String description;

    public Assignment(int ID, String name, String className, String date) {
        this.ID = ID;
        this.name = name;
        this.className = className;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}