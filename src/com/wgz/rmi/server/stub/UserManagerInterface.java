package com.wgz.rmi.server.stub;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.wgz.rmi.server.bean.Account;

public interface UserManagerInterface extends Remote{
    public String getUserName() throws RemoteException;
    public Account getAdminAccount() throws RemoteException;
}