package com.wgz.rpc.heartbeat;

import java.io.IOException;

import com.wgz.rpc.client.HeartbeatClient;
import com.wgz.rpc.heartbeat.impl.HeartbeatHandlerImpl;
import com.wgz.rpc.server.impl.ServiceCenter;

/**
 * @Description:心跳检测测试类
 * @author: wenguozhang 
 * @date:   2020年4月17日 下午2:06:26  
 */
public class HeartbeatTest {
 
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    ServiceCenter serviceServer = ServiceCenter.getInstance();
                    serviceServer.register(HeartbeatHandler.class, HeartbeatHandlerImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        Thread client1 = new Thread(new HeartbeatClient());
        client1.start();
        Thread client2 = new Thread(new HeartbeatClient());
        client2.start();
    }
}