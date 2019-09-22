package com.yp.rpc.server.spring;

import com.google.common.base.Preconditions;
import com.yp.rpc.server.RpcRequest;
import com.yp.rpc.server.RpcResponse;
import com.yp.rpc.server.RpcService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ServerContainor implements ApplicationContextAware {

    private final Map<String, Object> handler;

    public ServerContainor() {
        this.handler = new HashMap<>();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
         Map<String, Object> rpcServiceMap = applicationContext.getBeansWithAnnotation(RpcService.class);
         if(!rpcServiceMap.isEmpty()) {
             rpcServiceMap.forEach((name, bean) -> {
                 String interfaceName = bean.getClass().getAnnotation(RpcService.class).value().getName();
                 handler.put(interfaceName, bean);
             });
         }
    }

    public RpcResponse invoke(RpcRequest request) {
        Object service = handler.get(request.getServiceId());
        Preconditions.checkNotNull(service, "未注册服务" + request.getServiceId());
        Class<?> serviceClass = service.getClass();
        try {
            Method requestMethod = serviceClass.getDeclaredMethod(request.getMethodName(), request.getParameterTypes());
            Object result = requestMethod.invoke(service, request.getParameters());
            return new RpcResponse(request.getId(), result);
        } catch (NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("服务不可用" + request.getServiceId() + "." + request.getMethodName());
        } catch (IllegalAccessException e ) {
            throw new RuntimeException("服务无法访问" + request.getServiceId() + "." +request.getMethodName() + ", 考虑声明为public");
        }
    }
}
