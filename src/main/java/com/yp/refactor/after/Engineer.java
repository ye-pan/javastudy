package com.yp.refactor.after;

public class Engineer extends EmployeeType {
    @Override
    public int getTypeCode() {
        return EmployeeType.ENGINEER;
    }

    @Override
    public int payAmount(Employee employee) {
        return employee.getMonthlySalary();
    }
}
