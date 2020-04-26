package com.wgz.proxy.jdk;
/**
 * @author wgz
 *
 * @time 2019年4月7日下午4:41:10
 */
public class UserDaoProxy implements IUserDao{
    //目标对象
    private IUserDao target;
    public UserDaoProxy(IUserDao target){
        this.target=target;
    }

    public void save() {
        System.out.println("静态代理保存开始...");
        target.save();
        System.out.println("静态代理保存结束...");
    }

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
}