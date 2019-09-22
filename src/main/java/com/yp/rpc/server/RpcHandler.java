package com.yp.rpc.server;

import com.yp.rpc.server.spring.ServerContainor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcHandler extends SimpleChannelInboundHandler<RpcRequest> {
    private ServerContainor containor;
    public RpcHandler(ServerContainor containor) {
        this.containor = containor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RpcRequest msg) throws Exception {
        RpcResponse response = containor.invoke(msg);
        ctx.writeAndFlush(response);
    }
}
