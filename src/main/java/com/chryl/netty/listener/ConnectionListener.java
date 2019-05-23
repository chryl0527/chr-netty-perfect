package com.chryl.netty.listener;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * 重新连接:暂未实现
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class ConnectionListener implements ChannelFutureListener {

    //    private Channel channel;
////    private NettyClient client;
//
//    @Override
    public void operationComplete(ChannelFuture futureListener) throws Exception {
//        if (channel != null && channel.isActive()) {
//            return;
//        }
//        if (futureListener.isSuccess()) {
//              channel = futureListener.channel();
//            System.err.println(" Connect  successful ");
//        } else {
//            System.err.println(" Fail connect ,try connect to after 6s");
//
//            futureListener.channel().eventLoop().schedule(
//                    () -> reConnect()
//                    , 6, TimeUnit.SECONDS);
//        }
    }
}
