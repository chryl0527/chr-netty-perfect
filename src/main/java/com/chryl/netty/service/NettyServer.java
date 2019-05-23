package com.chryl.netty.service;


import com.chryl.netty.init.InitServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * Created By Chr on 2019/4/25.
 */
public class NettyServer {

    //发布服务
    public static void doPublisher(String host, int port) throws Exception {
        // Configure the service.
        //NioEventLoopGroup是用来处理I/O操作的线程池,
        //第一个称为“boss”，用来accept客户端连接，第二个称为“worker”，处理客户端数据的读写操作.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)//channel指定NIO方式
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new InitServerHandler());//childHandler用来配置具体的数据处理方式 ，可以指定编解码器，处理数据的Handler

            // Start the service.
            ChannelFuture f = b.bind(host, port).sync();//绑定端口启动服务

            // Wait until the service socket is closed.
            f.channel().closeFuture().sync();
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
