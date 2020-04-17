package com.wgz.rmi.server;

import java.rmi.RemoteException;

import com.wgz.rmi.server.bean.Account;
import com.wgz.rmi.server.stub.UserManagerInterface;


public class UserManagerImp implements UserManagerInterface {
    public UserManagerImp() throws RemoteException {

    }
    private static final long serialVersionUID = -3111492742628447261L;

    public String getUserName() throws RemoteException{
        return "TT";
    }
    public Account getAdminAccount() throws RemoteException{
        Account account=new Account();
        account.setUsername("TT");
        account.setPassword("123456");
        return account;
    }
}