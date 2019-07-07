package com.yp.memento;

import com.yp.designpatterns.Point;

public class MoveCommand {
    private Graphic graphic;
    private Point point;
    private ConstraintSolverMemento state;

    public MoveCommand(Graphic graphic, Point delta) {
        this.graphic = graphic;
        this.point = delta;
    }

    public void execute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        state = solver.createMemento();
        graphic.move(point);
        solver.solve();
    }

    public void unexecute() {
        ConstraintSolver solver = ConstraintSolver.getInstance();
        graphic.move(point);
        solver.setMemento(state);
        solver.solve();
    }
}
