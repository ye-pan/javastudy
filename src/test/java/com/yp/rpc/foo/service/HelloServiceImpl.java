package com.yp.rpc.foo.service;

import com.yp.rpc.server.RpcService;

@RpcService(HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        return "hello " + name;
    }

    private void foo() {
        System.out.println("for test private method");
    }
}
