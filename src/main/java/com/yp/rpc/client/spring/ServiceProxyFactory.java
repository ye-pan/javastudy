package com.yp.rpc.client.spring;

import com.yp.rpc.client.ServiceProxy;
import org.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ServiceProxyFactory<T> implements FactoryBean<T> {
    private final Class<T> interfaceType;

    public ServiceProxyFactory(Class<T> interfaceType) {
        this.interfaceType = interfaceType;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = new ServiceProxy(interfaceType);
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
