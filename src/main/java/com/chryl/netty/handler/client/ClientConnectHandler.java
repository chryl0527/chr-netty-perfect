package com.chryl.netty.handler.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoop;

import java.util.concurrent.TimeUnit;

import static com.chryl.netty.service.NettyClient.reConnect;


/**
 * Created By Chr on 2019/4/25.
 */
public class ClientConnectHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端掉线执行:重新连接
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.err.println("掉线了...");
        //使用过程中断线重连
        final EventLoop eventLoop = ctx.channel().eventLoop();
        eventLoop.schedule(() -> {

            try {

                reConnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }, 2, TimeUnit.SECONDS);
        super.channelInactive(ctx);
    }


    /**
     * 出错之后执行:立刻关闭连接
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.getStackTrace();
        ctx.close();
    }
}
