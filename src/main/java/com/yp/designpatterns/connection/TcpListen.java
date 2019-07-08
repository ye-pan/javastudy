package com.yp.designpatterns.connection;

public class TcpListen extends TcpState {

    public static TcpState newInstance() {
        return new TcpListen();
    }
}
