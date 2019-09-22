package com.yp.rpc.server;

public class RpcRequest {
    private final String id;
    private final String serviceId;
    private final String methodName;
    private final Object[] parameters;
    private final Class<?>[] parameterTypes;

    public RpcRequest() {
        this(null, null, null, null, null);
    }
    public RpcRequest(String id, String serviceId, String methodName, Object[] parameters, Class<?>[] parameterTypes) {
        this.id = id;
        this.serviceId = serviceId;
        this.methodName = methodName;
        this.parameters = parameters;
        this.parameterTypes = parameterTypes;
    }

    public String getServiceId() {
        return serviceId;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public String getId() {
        return id;
    }
}
