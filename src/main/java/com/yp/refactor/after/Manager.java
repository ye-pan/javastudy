package com.yp.refactor.after;

public class Manager extends EmployeeType {
    @Override
    public int getTypeCode() {
        return EmployeeType.MANAGER;
    }

    @Override
    public int payAmount(Employee employee) {
        return employee.getMonthlySalary() + employee.getBonus();
    }
}
