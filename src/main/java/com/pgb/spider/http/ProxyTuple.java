package com.pgb.spider.http;

/**
 * @author Clayburn
 * @date : 2018/1/16 17:33
 * @description 代理元组
 */
public class ProxyTuple {
    private static final Integer PORT_DEFAULT = 80;
    private String ip;
    private Integer port;

    public ProxyTuple(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    public String ip(){
        return ip;
    }

    public Integer port(){
        if(this.port == null){
            this.port = PORT_DEFAULT;
        }
        return port;
    }

    @Override
    public String toString() {
        return "ProxyTuple{" +
                "ip='" + ip + '\'' +
                ", port=" + port +
                '}';
    }
}
