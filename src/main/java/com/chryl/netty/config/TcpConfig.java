package com.chryl.netty.config;

/**
 * Created By Chr on 2019/4/25.
 */
public interface TcpConfig {

    //adress
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8082;

    //心跳配置
    public static final int READ_IDEL_TIME_OUT = 4; // 读超时
    public static final int WRITE_IDEL_TIME_OUT = 5;// 写超时
    public static final int ALL_IDEL_TIME_OUT = 7; // 所有超时

}
