package com.wgz.xml;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.io.IOUtils;

import com.wgz.xmlUtil.BeanToXml;
import com.wgz.xmlUtil.XmlToBean;

public class XmlUtil {
	private static Map<Class<?>, XmlToBean<?>> cache1 = new ConcurrentHashMap<>();
	private static Map<Class<?>, BeanToXml> cache2 = new ConcurrentHashMap<>();

	private static Map<String, String> xmlTemplates = new ConcurrentHashMap<>();

	public static <T> T toBean(Class<T> clazz, String xml) throws Exception{
		XmlToBean<T> convert = (XmlToBean<T>) cache1.get(clazz);
		if(convert == null){
			convert = new XmlToBean<>();
			convert.setClazz(clazz);
			cache1.put(clazz, convert);
		}
		return convert.convert(xml);
	}
	
	public static <T> T toXml(Object o, String template) throws Exception{
		Class<?> clazz = o.getClass();
		BeanToXml convert = cache2.get(o.getClass());
		if(convert == null){
			convert = new BeanToXml();
			convert.setClazz(clazz);
			cache2.put(clazz, convert);
		}
		return (T) convert.convert(o, template);
	}
	
	public static String getXmlTemplate(Class<?> clazz, String file){
		String template = xmlTemplates.get(file);
		if(template == null){
			try(InputStream is = clazz.getResourceAsStream(file); 
					ByteArrayOutputStream os = new ByteArrayOutputStream(0)){
				IOUtils.copy(is, os);//org.apache.commons.io.IOUtils
				template = os.toString("UTF-8");
				xmlTemplates.put(file, template);
			}catch(Exception e){
				return null;
			}
		}
		return template;
	}
	
	public static void reset(){
		xmlTemplates.clear();
	}
	
}

































