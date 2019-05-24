package com.chryl.myNetty.code;


import com.chryl.myNetty.bean.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;
import java.util.List;

//import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 未实现
 * <p>
 * Created By Chr on 2019/5/24.
 */
public class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage> {

    ChrMarshallingEncoder chrMarshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.chrMarshallingEncoder = new ChrMarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {


    }
}
