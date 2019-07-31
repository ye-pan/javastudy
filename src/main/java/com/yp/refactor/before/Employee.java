package com.yp.refactor.before;

public class Employee {

    private EmployeeType type;

    private int monthlySalary;

    private int commission;

    private int bonus;

    public int payAmount() {
        switch(getType()) {
            case EmployeeType.ENGINEER:
                return monthlySalary;
            case EmployeeType.SALESMAN:
                return monthlySalary + commission;
            case EmployeeType.MANAGER:
                return monthlySalary + bonus;
            default:
                throw new RuntimeException("Incorrect Employee");
        }
    }

    private int getType() {
        return type.getTypeCode();
    }
}
