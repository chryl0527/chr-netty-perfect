package com.chryl.netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * StringDecoder:继承MessageToMessageDecoder，重写decode方法，将ByteBuf数据直接转成字符串进行输出，解码完成。
 * 模仿:
 * 对象解码器:接收ByteBuf数据，将ByteBuf反序列化成对象
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
//        Object obj = ByteUtils.byteToObject(ByteUtils.read(in));
//        out.add(obj);
    }
}
