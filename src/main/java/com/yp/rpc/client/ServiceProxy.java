package com.yp.rpc.client;

import com.yp.rpc.server.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

public class ServiceProxy implements InvocationHandler {
    private Invoker invoker;

    public ServiceProxy(Class<?> clazz) {

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String id = UUID.randomUUID().toString();
        String serviceId = method.getDeclaringClass().getName();
        RpcRequest request = new RpcRequest(id, serviceId, method.getName(), args, method.getParameterTypes());
        RpcClientHandler handler = ConnectManage.getInstance().chooseHandler();
        RPCFuture future = handler.sendRequest(request);
        return future.get();
    }
}
