package com.yp.designpatterns.connection;

public class TcpConnection {
    private TcpState state;

    public TcpConnection() {
        state = TcpClosed.newInstance();
    }

    public void changeState(TcpState state) {
        this.state = state;
    }

    public void activeOpen() {
        state.activeOpen(this);
    }

    public void passiveOpen() {
        state.passiveOpen(this);
    }

    public void close() {
        state.close(this);
    }

    public void acknowledge() {
        state.acknowledge(this);
    }

    public void synchronize() {
        state.synchronize(this);
    }
}
