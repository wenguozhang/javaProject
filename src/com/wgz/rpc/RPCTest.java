package com.wgz.rpc;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.wgz.rpc.client.RPCClient;
import com.wgz.rpc.server.HelloService;
import com.wgz.rpc.server.Server;
import com.wgz.rpc.server.impl.HelloServiceImpl;
import com.wgz.rpc.server.impl.ServiceCenter;

public class RPCTest {
 
    public static void main(String[] args) throws IOException {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Server serviceServer = new ServiceCenter(8088);
                    serviceServer.register(HelloService.class, HelloServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        HelloService service = RPCClient.getRemoteProxyObj(HelloService.class, new InetSocketAddress("localhost", 8088));
        System.out.println(service.sayHi("test"));
    }
}