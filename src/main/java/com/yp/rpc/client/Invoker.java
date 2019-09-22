package com.yp.rpc.client;

import com.yp.rpc.server.RpcRequest;
import com.yp.rpc.server.RpcResponse;

public interface Invoker {
    RpcResponse invoke(RpcRequest request);
}
