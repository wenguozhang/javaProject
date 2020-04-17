package com.wgz.rpc.heartbeat;
/**
 * @Description:心跳处理类接口
 * @author: wenguozhang 
 * @date:   2020年4月17日 上午10:09:50  
 */
public interface HeartbeatHandler {
    public Cmder sendHeartBeat(HeartbeatEntity info);
}