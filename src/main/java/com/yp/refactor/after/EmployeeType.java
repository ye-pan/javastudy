package com.yp.refactor.after;

public abstract class EmployeeType {
    public static final int ENGINEER = 0;
    public static final int SALESMAN = 1;
    public static final int MANAGER = 2;

    public abstract int getTypeCode();

    public abstract int payAmount(Employee employee);
}
