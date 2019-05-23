package com.chryl.netty.init;


import com.chryl.netty.config.TcpConfig;
import com.chryl.netty.handler.HeartbeatServerHandler;
import com.chryl.netty.handler.client.ClientBusinessHandler;
import com.chryl.netty.handler.client.ClientConnectHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * ClientHandler
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class InitClientHandler extends ChannelInitializer<Channel> {

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        /**
         * 编码解码
         */
        pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
        pipeline.addLast("encoder", new ObjectEncoder());
        //禁用缓存，有可能导致使用缓存出错
        pipeline.addLast("decoder", new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

        /**
         * 超时心跳机制:具有读超时,写超时,读写超时
         * 断连重连机制:断开连接之后5s重新心跳连接
         */
        pipeline.addLast(new IdleStateHandler(TcpConfig.READ_IDEL_TIME_OUT, TcpConfig.WRITE_IDEL_TIME_OUT, TcpConfig.ALL_IDEL_TIME_OUT, TimeUnit.SECONDS)); // 1
        //心跳机制:用来处理超时时，发送心跳
        pipeline.addLast("ping", new HeartbeatServerHandler()); // 2
        //连接机制
        pipeline.addLast("conn", new ClientConnectHandler());

        /**
         * 业务处理机制
         */
        pipeline.addLast(new ClientBusinessHandler());


    }

//    怎么解决 粘包和半包(拆包)？
//
//    消息定长度，传输的数据大小固定长度，例如每段的长度固定为100字节，如果不够空位补空格
//
//    在数据包尾部添加特殊分隔符，比如下划线，中划线等
//
//    将消息分为消息头和消息体，消息头中包含表示信息的总长度
//
//    Netty提供了多个解码器，可以进行分包的操作，分别是：
//
//    LineBasedFrameDecoder （回车换行分包）
//
//    DelimiterBasedFrameDecoder（特殊分隔符分包）
//
//    FixedLengthFrameDecoder（固定长度报文来分包）
//
//    LengthFieldBasedFrameDecoder（自定义长度来分包）
}
