package com.wgz.jetty;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

import com.wgz.frame.options.OptionLoader;
import com.wgz.frame.options.Options;

public class JettyServer{
	/**
	 * WEBӦ��·�����������·����WebContent��
	 */
	private final static String WEBAPP_PATH = "www";
	/**
	 * WEBӦ��������
	 */
	private final static String CONTEXT_PATH = "/wgz";
	/**
	 * WEBӦ�÷��ʶ˿�
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
	 * ����jetty������
	 * @throws Exception 
	 */
	private static void start(){
		try {
			long start = System.currentTimeMillis();
		    System.out.println("************����myProject************");
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
			System.out.println("jetty������ʱ��" + (System.currentTimeMillis() - start) + "ms");	
			System.out.println("WEBӦ�ö˿�:" + PORT + "\tWEBӦ�������ģ�" + CONTEXT_PATH + "\nWEBӦ�õ�ַ��http://localhost" + (PORT == 80 ?  "" : ":" + PORT) + CONTEXT_PATH);
			server.join();
		} catch (Exception e) {
			System.err.println("����jetty����ʧ�ܣ�");
			e.printStackTrace();
		}
	}	
}