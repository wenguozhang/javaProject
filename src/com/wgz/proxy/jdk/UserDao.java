package com.wgz.proxy.jdk;
/**
 * @author wgz
 *
 * @time 2019年4月7日下午4:40:05
 */
public class UserDao implements IUserDao {
    public void save() {
        System.out.println("------保存操作------");
    }
    public void delete() {
        System.out.println("------删除操作------");
    }
}