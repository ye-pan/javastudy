package com.yp.designpatterns.connection;

public class TcpEstablished extends TcpState {
    public static TcpState newInstance() {
        return new TcpEstablished();
    }
}
