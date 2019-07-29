package com.yp.refactor.before;

public class Person {

    public static final int O = 0;
    public static final int A = 1;
    public static final int B = 2;
    public static final int AB = 3;

    private String name;

    private String officeAreaCode;

    private String officeNumber;

    private Department department;

    private int booldGroup;

    public Person(int booldGroup) {
        this.booldGroup = booldGroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeAreaCode() {
        return officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.officeAreaCode = officeAreaCode;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getTelephoneNumber() {
        return "(" + officeAreaCode + ") " + officeNumber;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public int getBooldGroup() {
        return booldGroup;
    }

    public void setBooldGroup(int booldGroup) {
        this.booldGroup = booldGroup;
    }
}
