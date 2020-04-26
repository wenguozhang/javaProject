package com.wgz.proxy.jdk;

import java.lang.reflect.*;
/**
 * @author wgz
 *
 * @time 2019年4月7日下午4:45:23
 */
public class ProxyFactory{

    //目标对象
    private Object target;
    public ProxyFactory(Object target){
        this.target=target;
    }

   //代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("动态代理开始...");
                        //使用反射的mothod.invoke调用目标对象方法
                        Object returnValue = method.invoke(target, args);
                        System.out.println("动态代理结束...");
                        return returnValue;
                    }
                }
        );
    }

}