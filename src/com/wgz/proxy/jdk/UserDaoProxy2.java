package com.wgz.proxy.jdk;
/**
 * @author wgz
 *
 * @time 2019年4月7日下午4:41:10
 */
public class UserDaoProxy2{
    //目标对象
    private UserDao target;
    public UserDaoProxy2(UserDao target){
        this.target=target;
    }

    public void save() {
        System.out.println("静态代理保存开始...");
        target.save();
        System.out.println("静态代理保存结束...");
    }

	public void delete() {
		// TODO Auto-generated method stub
		
	}
}