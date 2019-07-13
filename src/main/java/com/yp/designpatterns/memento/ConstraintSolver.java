package com.yp.designpatterns.memento;

public class ConstraintSolver {
    public void solve() {

    }

    public void addConstraint(Graphic start, Graphic end) {

    }

    public void removeContraint(Graphic start, Graphic end) {

    }

    public ConstraintSolverMemento createMemento() {
        return null;
    }

    public void setMemento(ConstraintSolverMemento memento) {

    }

    public static ConstraintSolver getInstance() {
        return ConstraintSolverHolder.INSTANCE;
    }

    private static class ConstraintSolverHolder {
        static final ConstraintSolver INSTANCE = new ConstraintSolver();
    }
}
