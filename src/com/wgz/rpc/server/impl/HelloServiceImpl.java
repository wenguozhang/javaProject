package com.wgz.rpc.server.impl;

import com.wgz.rpc.server.HelloService;

public class HelloServiceImpl implements HelloService {
 
    public String sayHi(String name) {
        return "Hi, " + name;
    }
 
}