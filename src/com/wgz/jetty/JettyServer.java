package com.wgz.jetty;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.wgz.frame.options.OptionLoader;
import com.wgz.frame.options.Options;

public class JettyServer{
	/**
	 * WEB应用路径，可用相对路径（WebContent）
	 */
	private final static String WEBAPP_PATH = "www";
	/**
	 * WEB应用上下文
	 */
	private final static String CONTEXT_PATH = "/wgz";
	/**
	 * WEB应用访问端口
	 */
	private final static int PORT = 8081;
	
	public static void main(String[] args){
		
		init();
		
		
		start();
	}

	  
	public static void init(){
		OptionLoader loader = new OptionLoader();
		loader.add(new File("conf"));
//		loader.add("com.wgz.frame.utils");
		try {
			Options options = loader.load();
			System.out.println(options);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 启动jetty服务器
	 * @throws Exception 
	 */
	private static void start(){
		try {
			long start = System.currentTimeMillis();
		    System.out.println("************启动myProject************");
		    System.setProperty("org.eclipse.jetty.util.URI.charset", "UTF-8");
			Server server = new Server(PORT);
			
			WebAppContext wac = new WebAppContext(server, WEBAPP_PATH, CONTEXT_PATH);
			wac.setClassLoader(Thread.currentThread().getContextClassLoader());
			wac.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
			wac.setServer(server);
            server.setHandler(wac);
            server.setStopAtShutdown(true);
			server.setStopTimeout(63000);
			
			server.start();
			System.out.println("jetty启动耗时：" + (System.currentTimeMillis() - start) + "ms");	
			System.out.println("WEB应用端口:" + PORT + "\tWEB应用上下文：" + CONTEXT_PATH + "\nWEB应用地址：http://localhost" + (PORT == 80 ?  "" : ":" + PORT) + CONTEXT_PATH);
			server.join();
		} catch (Exception e) {
			System.err.println("启动jetty服务失败！");
			e.printStackTrace();
		}
	}	
}