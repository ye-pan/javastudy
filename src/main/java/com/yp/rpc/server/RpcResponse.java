package com.yp.rpc.server;

public class RpcResponse {
    private final String id;
    private final Object result;

    public RpcResponse() {
        this(null, null);
    }

    public RpcResponse(String id, Object result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public Object getResult() {
        return result;
    }
}
