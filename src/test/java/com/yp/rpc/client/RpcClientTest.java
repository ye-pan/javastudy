package com.yp.rpc.client;

import com.yp.rpc.foo.SampleConfiguration;
import com.yp.rpc.foo.service.HelloService;
import org.junit.Test;

import static org.junit.Assert.*;

public class RpcClientTest {

    @Test
    public void helloTest1() {
        RpcClient rpcClient = new RpcClient(SampleConfiguration.SERVER_ADDRESS);
        HelloService helloService = rpcClient.create(HelloService.class);
        String result = helloService.greeting("World");
        assertEquals("hello World", result);
    }
}