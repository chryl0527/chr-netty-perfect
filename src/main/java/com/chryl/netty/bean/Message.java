package com.chryl.netty.bean;

import java.io.Serializable;

/**
 * Created By Chr on 2019/4/25.
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -3256369331804108181L;

    private String id;
    private String content;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
