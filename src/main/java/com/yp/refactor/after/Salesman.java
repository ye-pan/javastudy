package com.yp.refactor.after;

public class Salesman extends EmployeeType {
    @Override
    public int getTypeCode() {
        return EmployeeType.SALESMAN;
    }

    @Override
    public int payAmount(Employee employee) {
        return employee.getMonthlySalary() + employee.getCommission();
    }
}
