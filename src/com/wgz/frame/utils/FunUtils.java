package com.wgz.frame.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.springframework.util.Assert;


/**
 * @Description:工具类  map判空、包含，年份、月份匹配 
 * @author: wenguozhang 
 * @date:   2019年5月24日 上午10:25:31  
 */
public class FunUtils {
	
	/*--------------------tools--------------------*/
	/**
	 * 校验map集合中指定key值是否都有值
	 * @param map		校验集合
	 * @param parmaKeys	校验的key数组
	 * @return
	 */
	public static boolean checkValueByKeys(Map<? extends String, ? extends Object> map, 
			String [] keys) {
		if(isNull(map) || isNull(keys)) {
			return true;
		}
		boolean result = false;
		int len = keys.length;
		for (int i = 0; i < len; i++) {
			Object temp = map.get(keys[i]);
			if (isNull(temp)) {
				result = true;
				break;
			}
		}
		return result;
	}
	
	/**
     * 获得当前执行方法的方法名称
     */
	public static String getCurrentMethodName() {
		Thread thread = Thread.currentThread();
  		StackTraceElement[] stackTrace = thread.getStackTrace();
  		String methodName = stackTrace[2].getMethodName();
  		return methodName;
	}
	/**
	 * 判断数组中是否包含指定的元素
	 * @param ts	数组
	 * @param t		指定元素	
	 * @return
	 */
	public static <T> boolean ArraysContains(T [] ts, T t ) {
		Assert.notNull(ts, "error : array can not be null");
		Assert.notNull(t, "error : target element can not be null");
		return Arrays.asList(ts).contains(t);	
	}
	/**
	 * 判断一个4位的字符串是不是一个年份的字符串
	 * @param year      年份字符串
	 * @return          年份格式true,不是false
	 */
	public static Boolean isYearNumber(String year) {
		Assert.notNull(year, "error : year string can not be null");
		String reg="1949|19[5-9]\\d|20\\d{2}|2100";
		return year.matches(reg);
	}
	
	/**
	 * 判断一个2位的字符串是不是一个月份的字符串
	 * @param month		月份的字符串
	 * @return			月份格式true,不是false
	 */
	public static Boolean isMonthNumber(String month) {
		Assert.notNull(month, "error : month string can not be null");
		String reg="0?[1-9]|1[0-2]";
		return month.matches(reg);
	}
	
	/*--------------------format--------------------*/
	/**
	 * 格式化指定参数成字符串
	 * @param t		指定参数
	 * @return		null
	 */
	public static <T> String formatToString(T t) {
		return t == null ? "" : t.toString();
	}
	
	/*--------------------isNull--------------------*/
	/**
	 * 判断map集合是否为空或者null
	 * @param map	指定map集合
	 * @return		null或者空返回true
	 */
	public static boolean isNull(Map<?, ?> map) {
		return map == null || map.isEmpty();
	} 
	/**
	 * 判断collection集合是否为空或者null
	 * @param coll	指定集合
	 * @return		null或者空返回true
	 */
	public static boolean isNull(Collection<?> coll) {
		return coll == null || coll.isEmpty();
	}
	/**
	 * 判断指定数组是否为空或者null
	 * @param ts	指定数组
	 * @return		null或者空返回true
	 */
	public static <T> boolean isNull(T[] ts) {
		return ts == null || ts.length == 0;
	}
	/**
	 * 判断指定对象是否为空或者null
	 * @param obj	指定对象
	 * @return		null或者空返回true
	 */
	public static boolean isNull(Object obj) {
		return obj == null || "".equals(obj);
	}
	/**
	 * 判断指定字符转是否为空或者null
	 * @param str	指定字符串
	 * @return		null或者空或者"null"返回true
	 */
	public static boolean isNull(String str) {
		return str == null || "".equals(str) || "null".equals(str);
	}
	
	/**
	 * 格式化字符串代替toString的使用
	 * null的情况返回""
	 * @param t 	转换的对象
	 * @return		装换后的字符串
	 */
	public static <T> String formatString(T t) {
		if (isNull(t)) {
			return "";
		}
		return t.toString();
	}
	
}
