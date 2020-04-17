package com.wgz.test;

import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class Test{
	public static void main(String[] args) {
		Socket client = null;
		try{
			client = new Socket("47.106.149.22", 8080);
			System.out.println("端口已开放");
			client.close();
		}catch(Exception e){
			System.out.println("端口未开放");
		}
		
		URL url;  
        try {  
             url = new URL("http://sltest.front.shang-lian.com/interface");  
//        	 url = new URL("http://bjsl_sf.hnwmxy.com");  
        	 URLConnection co =  url.openConnection();
             co.setConnectTimeout(2000);
             co.connect();
             System.out.println("连接可用");  
        } catch (Exception e1) {  
             System.out.println("连接打不开!");  
             url = null;  
        }  
	}
		
}
