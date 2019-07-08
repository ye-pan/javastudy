package com.yp.designpatterns.connection;

public class TcpState {
    public void activeOpen(TcpConnection tcpConnection) {
        System.out.println("active open.");
    }

    public void passiveOpen(TcpConnection tcpConnection) {
        System.out.println("passive open.");
    }

    public void close(TcpConnection tcpConnection) {
        System.out.println("close.");
    }

    public void acknowledge(TcpConnection tcpConnection) {
        System.out.println("acknowledge.");
    }

    public void synchronize(TcpConnection tcpConnection) {
        System.out.println("synchronize.");
    }
}
