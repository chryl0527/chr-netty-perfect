package com.chryl.netty.service;


import com.chryl.netty.config.TcpConfig;
import com.chryl.netty.init.InitClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created By Chr on 2019/4/25.
 */
public class NettyClient {

    private static Channel channel;
    private static Bootstrap bootstrap;

    //连接
    public static void doConnect() {

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .handler(new InitClientHandler());

            reConnect();

        } catch (Exception e) {
            //e.printStackTrace();
            System.err.println(" Netty服务端连接失败:============= ");
        }
//        finally {
//             group.shutdownGracefully();
//        }
    }


    //重连,客户端不关流--->本类直接重连
    public static void reConnect() {
        if (channel != null && channel.isActive()) {
            return;
        }

//        ChannelFuture future = null;
//        try {
//            future = bootstrap.connect(TcpConfig.HOST, TcpConfig.PORT).sync();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        ChannelFuture future = bootstrap.connect(TcpConfig.HOST, TcpConfig.PORT);


        future.addListener((ChannelFutureListener) futureListener -> {
            if (futureListener.isSuccess()) {
                channel = futureListener.channel();
                System.err.println(" Connect  successful ");
//                channel.writeAndFlush("客户端 正在发送心跳包 ");

                //对象传输数据
//                Message message = new Message();
//                message.setId(UUID.randomUUID().toString().replaceAll("-", ""));
//                message.setContent("hello yinjihuan");
//                channel.writeAndFlush(message);

            } else {
                System.err.println(" Fail connect ,try connect to after 5s");

                futureListener.channel().eventLoop().schedule(
                        () -> reConnect(), 5, TimeUnit.SECONDS);
            }
        });
    }

}
