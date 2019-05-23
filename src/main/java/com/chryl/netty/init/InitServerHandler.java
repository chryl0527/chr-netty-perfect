package com.chryl.netty.init;


import com.chryl.netty.config.TcpConfig;
import com.chryl.netty.handler.HeartbeatServerHandler;
import com.chryl.netty.handler.server.ServerBusinessHandler;
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
 * ServerHandler
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class InitServerHandler extends ChannelInitializer<Channel> {

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
         */
        pipeline.addLast(new IdleStateHandler(TcpConfig.READ_IDEL_TIME_OUT, TcpConfig.WRITE_IDEL_TIME_OUT, TcpConfig.ALL_IDEL_TIME_OUT, TimeUnit.SECONDS)); // 1
        //心跳机制:用来处理超时时，发送心跳
        pipeline.addLast(new HeartbeatServerHandler()); // 2
        /**
         * 业务处理机制
         */
        pipeline.addLast(new ServerBusinessHandler());

    }
}
