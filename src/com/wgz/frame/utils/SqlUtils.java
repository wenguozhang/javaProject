package com.wgz.frame.utils;

/**
 * @author wgz
 *
 * @time 2018��9��14������3:30:12
 */
public class SqlUtils {
	public static void close(AutoCloseable o){
		if(o != null)
			try{
				o.close();
			} catch(Exception e){
				
			}
	}
}
