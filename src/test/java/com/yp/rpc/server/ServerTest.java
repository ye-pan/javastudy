package com.yp.rpc.server;

import com.yp.rpc.foo.SampleConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ServerTest {

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SampleConfiguration.class);
    }

}