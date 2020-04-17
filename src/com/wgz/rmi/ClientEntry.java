package com.wgz.rmi;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.wgz.rmi.server.stub.UserManagerInterface;

public class ClientEntry {
    
    public static void main(String []args){
        
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",2002);
            UserManagerInterface userManager = (UserManagerInterface)registry.lookup("userManager");
            System.out.println("name:"+userManager.getAdminAccount().getUsername()
                    +"pwd:"+userManager.getAdminAccount().getPassword());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}