package com.chryl.myNetty.bean;

import com.chryl.myNetty.header.Header;

/**
 * 自定义消息格式
 * <p>
 * Created By Chr on 2019/5/24.
 */
public class NettyMessage {

    private Header header;//消息头
    private Object body;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "NettyMessage{" +
                "header=" + header +
                ", body=" + body +
                '}';
    }
}
