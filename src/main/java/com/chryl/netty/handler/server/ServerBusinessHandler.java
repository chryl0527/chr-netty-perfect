package com.chryl.netty.handler.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created By Chr on 2019/4/25.
 */
public class ServerBusinessHandler extends ChannelInboundHandlerAdapter {
    /**
     * @param ctx 发送
     * @param msg 接收
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //msg：拿到服务端写过来的内容
        System.err.println(" 已经收到消息 : " + msg.toString());
        ctx.write(" 服务端   已经收到了消息 :" + msg.toString());

        ctx.flush();
        ctx.close();

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
