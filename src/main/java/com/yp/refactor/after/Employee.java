package com.yp.refactor.after;

public class Employee {

    private EmployeeType type;

    private int monthlySalary;

    private int commission;
    
    private int bonus;

    public int payAmount() {
        return type.payAmount(this);
    }

    private int getType() {
        return type.getTypeCode();
    }

    public int getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(int monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
