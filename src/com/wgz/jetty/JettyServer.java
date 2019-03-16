package com.wgz.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

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
		try {
			start();
		} catch (Exception e) {
			System.err.println("启动jetty服务失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 启动jetty服务器
	 * @throws Exception 
	 */
	private static void start() throws Exception{
		long start = System.currentTimeMillis();
	    System.out.println("************启动myProject************");
	    System.setProperty("org.eclipse.jetty.util.URI.charset", "UTF-8");
		Server s = new Server(PORT);
		
		WebAppContext wac = new WebAppContext(s, WEBAPP_PATH, CONTEXT_PATH);
		wac.setClassLoader(Thread.currentThread().getContextClassLoader());
		wac.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		wac.setServer(s);
//        s.setHandler(wac);

		s.start();
		System.out.println("jetty启动耗时：" + (System.currentTimeMillis() - start) + "ms");	
		System.out.println("WEB应用端口:" + PORT + "\tWEB应用上下文：" + CONTEXT_PATH + "\nWEB应用地址：http://localhost" + (PORT == 80 ? "" : ":" + PORT) + CONTEXT_PATH);
		s.join();
	}	
}