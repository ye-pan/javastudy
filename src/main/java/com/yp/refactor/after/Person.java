package com.yp.refactor.after;

public class Person {

    private String name;

    private TelephoneNumber officeTelephone;

    private Department department;

    private BooldGroup booldGroup;

    public Person(int booldGroup) {
        this.booldGroup = BooldGroup.code(booldGroup);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeAreaCode() {
        return officeTelephone.getAreaCode();
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        officeTelephone.setAreaCode(officeAreaCode);
    }

    public String getOfficeNumber() {
        return officeTelephone.getNumber();
    }

    public void setOfficeNumber(String officeNumber) {
        officeTelephone.setNumber(officeNumber);
    }

    public String getTelephoneNumber() {
        return "(" + getOfficeAreaCode() + ") " + getOfficeNumber();
    }

    public Person getManager() {
        return department.getManager();
    }

    public int getBooldGroupCode() {
        return booldGroup.getCode();
    }

    public BooldGroup getBooldGroup() {
        return booldGroup;
    }

    public void setBooldGroup(int booldGroup) {
        this.booldGroup = BooldGroup.code(booldGroup);
    }
}
