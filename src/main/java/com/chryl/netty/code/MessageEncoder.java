package com.chryl.netty.code;

import com.chryl.netty.bean.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * StringEncoder:通过继承MessageToMessageEncoder，重写encode方法来进行编码操作，就是将字符串进行输出即可。
 * 模仿:
 * 对象编码器:将对象序列化成字节，通过ByteBuf形式进行传输，ByteBuf是一个byte存放的缓冲区，提供了读写操作。
 * <p>
 * Created By Chr on 2019/4/25.
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
//        byte[] datas = ByteUtils.objectToByte(message);
//        out.writeBytes(datas);
//        ctx.flush();
    }

}
