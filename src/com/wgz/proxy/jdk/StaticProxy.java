package com.wgz.proxy.jdk;
/**
 * @author wgz
 *
 * @time 2019年4月7日下午4:44:50
 */
public class StaticProxy {
    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
//        UserDaoProxy proxy = new UserDaoProxy(target);
        UserDaoProxy2 proxy = new UserDaoProxy2(target);

        proxy.save();//ִ代理对象保存
        
        proxy.delete();
    }
}