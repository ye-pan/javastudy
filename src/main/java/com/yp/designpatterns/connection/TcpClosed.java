package com.yp.designpatterns.connection;

public class TcpClosed extends TcpState {
    public static TcpState newInstance() {
        return new TcpClosed();
    }

    @Override
    public void activeOpen(TcpConnection tcpConnection) {
        tcpConnection.changeState(TcpEstablished.newInstance());
    }

    @Override
    public void passiveOpen(TcpConnection tcpConnection) {
        tcpConnection.changeState(TcpListen.newInstance());
    }

    @Override
    public void close(TcpConnection tcpConnection) {
        tcpConnection.changeState(TcpListen.newInstance());
    }
}
