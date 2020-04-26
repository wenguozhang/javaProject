package com.wgz.proxy.jdk;
/**
 * @Description:动态代理
 * @author: wenguozhang 
 * @date:   2020年4月23日 下午4:46:42  
 */
public class DynamicProxy {
    public static void main(String[] args) {
        //目标对象
        IUserDao target = new UserDao();
        System.out.println(target.getClass());

        //代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());

        //代理方法执行
        proxy.save();
        proxy.delete();
    }
}