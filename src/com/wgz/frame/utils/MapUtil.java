package com.wgz.frame.utils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.wgz.xmlUtil.DoubleConverter;
import com.wgz.xmlUtil.IntegerConverter;
import com.wgz.xmlUtil.LongConverter;

public class MapUtil {
	private static IntegerConverter ic1 = new IntegerConverter(true);
	private static IntegerConverter ic2 = new IntegerConverter(false);
	private static LongConverter lc1 = new LongConverter(true);
	private static LongConverter lc2 = new LongConverter(false);
	private static DoubleConverter dc1 = new DoubleConverter(true);
	private static DoubleConverter dc2 = new DoubleConverter(false);

	public static Integer integer(Map<String, Object> params, String name) {
		return integer(params, name, false);
	}

	public static Integer integer(Map<String, Object> params, String name, boolean nullable) {
		try {
			if (nullable) {
				return ic1.convert(params.get(name));
			} else {
				return ic2.convert(params.get(name));
			}
		} catch (Exception e) {
			// throw new UserException(ErrorCode.参数错误, name);
			return 0;
		}
	}

	public static Long longx(Map<String, Object> params, String name) {
		return longx(params, name, false);
	}

	public static Long longx(Map<String, Object> params, String name, boolean nullable) {
		try {
			if (nullable) {
				return lc1.convert(params.get(name));
			} else {
				return lc2.convert(params.get(name));
			}
		} catch (Exception e) {
			// throw new UserException(ErrorCode.参数错误, name);
			return 0L;
		}
	}

	public static Double doublex(Map<String, Object> params, String name) throws Exception {
		return doublex(params, name, false);
	}

	public static Double doublex(Map<String, Object> params, String name, boolean nullable) {
		Object value = params.get(name);
		try {
			if (nullable) {
				return dc1.convert(value);
			} else {
				return dc2.convert(value);
			}
		} catch (Exception e) {
			throw new RuntimeException("数�?�格式错�?: " + value);
		}
	}

	/**
	 * 从传入的Map参数中，读取name指定的String类value
	 * 
	 * @param params
	 *            传入的map
	 * @param name
	 *            读取的key
	 * @return
	 */
	public static String string(Map<String, Object> params, String name) {
		if (params == null) {
			return null;
		}
		Object o = params.get(name);
		if (o == null) {
			return null;
		}
		return o.toString();
	}

	public static Map<String, Object> clone(Map<String, Object> params) {
		Map<String, Object> m = new HashMap<>();
		m.putAll(params);
		return m;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T map2Java(T javaBean, Map map) {
		try {
			// 获取javaBean属�??
			BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
			// 创建 JavaBean 对象
			Object obj = javaBean.getClass().newInstance();

			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			if (propertyDescriptors != null && propertyDescriptors.length > 0) {
				String propertyName = null; // javaBean属�?�名
				Object propertyValue = null; // javaBean属�?��??
				for (PropertyDescriptor pd : propertyDescriptors) {
					propertyName = pd.getName();
					if (map.containsKey(propertyName)) {
						propertyValue = map.get(propertyName);
						if (propertyValue == null) {
							propertyValue = new String("");
						}
						pd.getWriteMethod().invoke(obj, new Object[] { propertyValue.toString() });
					}
				}
				return (T) obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将一�? JavaBean 对象转化为一�? Map
	 * 
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来�? Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失�?
	 * @throws IllegalAccessException
	 *             如果实例�? JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属�?�的 setter 方法失败
	 */
	public static Map<String, Object> convertBean(Object bean)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}

	public static byte[] Map2Byte(Map<String, Object> params) {
		if (params == null || params.isEmpty()) {
			return null;
		}
		try {
			ByteArrayOutputStream bo = new ByteArrayOutputStream();
			ObjectOutputStream os = new ObjectOutputStream(bo);
			os.writeObject(params);
			return bo.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}
}
