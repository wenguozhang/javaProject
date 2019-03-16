package com.wgz.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

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
		try {
			start();
		} catch (Exception e) {
			System.err.println("����jetty����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	/**
	 * ����jetty������
	 * @throws Exception 
	 */
	private static void start() throws Exception{
		long start = System.currentTimeMillis();
	    System.out.println("************����myProject************");
	    System.setProperty("org.eclipse.jetty.util.URI.charset", "UTF-8");
		Server s = new Server(PORT);
		
		WebAppContext wac = new WebAppContext(s, WEBAPP_PATH, CONTEXT_PATH);
		wac.setClassLoader(Thread.currentThread().getContextClassLoader());
		wac.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
		wac.setServer(s);
//        s.setHandler(wac);

		s.start();
		System.out.println("jetty������ʱ��" + (System.currentTimeMillis() - start) + "ms");	
		System.out.println("WEBӦ�ö˿�:" + PORT + "\tWEBӦ�������ģ�" + CONTEXT_PATH + "\nWEBӦ�õ�ַ��http://localhost" + (PORT == 80 ? "" : ":" + PORT) + CONTEXT_PATH);
		s.join();
	}	
}