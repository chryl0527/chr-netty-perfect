package com.chryl.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * 心跳
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

    // Return a unreleasable view on the given ByteBuf
    // which will just ignore release and retain calls.
    //发送heartbeat
    private static final ByteBuf HEARTBEAT_SEQUENCE
            = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heart-Beat", CharsetUtil.UTF_8));  // 1

    /**
     * 心跳超时事件方法,if-else和switch-case
     *
     * @param ctx
     * @param evt IdleStateEvent ： 超时的事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

        if (evt instanceof IdleStateEvent) {  // 2
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";

            //(1)   if-else
//            if (event.state() == IdleState.READER_IDLE) {
//                type = "read idle";
//            } else if (event.state() == IdleState.WRITER_IDLE) {
//                type = "write idle";
//            } else if (event.state() == IdleState.ALL_IDLE) {
//                type = "all idle";
//            }


            //(2)   switch-case
            switch (event.state()) {
                case READER_IDLE:
                    type = "read idle";
                    break;
                case WRITER_IDLE:
                    type = "write idle";
                    break;
                case ALL_IDLE:
                    type = "all idle";
                    break;
                default:
                    break;
            }

            //发送超时
            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(
                    ChannelFutureListener.CLOSE_ON_FAILURE);  // 3

            System.out.println(ctx.channel().remoteAddress() + "超时类型：" + type);
            ctx.close();
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
