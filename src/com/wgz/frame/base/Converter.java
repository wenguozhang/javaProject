package com.wgz.frame.base;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public abstract class Converter<T> {
	private static final Map<Class<?>, Converter<?>> map = new HashMap<Class<?>, Converter<?>>();
	static {
		map.put(Short.TYPE, new ShortConverter(false));
		map.put(Short.class, new ShortConverter(true));
		map.put(Integer.TYPE, new IntegerConverter(false));
		map.put(Integer.class, new IntegerConverter(true));
		map.put(Long.TYPE, new LongConverter(false));
		map.put(Long.class, new LongConverter(true));
		map.put(Float.TYPE, new FloatConverter(false));
		map.put(Float.class, new FloatConverter(true));
		map.put(Double.TYPE, new DoubleConverter(false));
		map.put(Double.class, new DoubleConverter(true));
		map.put(Boolean.TYPE, new BooleanConverter(false));
		map.put(Boolean.class, new BooleanConverter(true));
		map.put(String.class, new StringConverter());
		map.put(Date.class, new DateConverter());
		map.put(byte[].class, new ByteArrayConverter());
		map.put(BigDecimal.class, new BigDecimalConverter(false));
		map.put(Timestamp.class, new TimestampConverter());
	}

	private static Converter<Enum<?>> enumConverter = new EnumConverter();

	@SuppressWarnings("unchecked")
	public static <TT> Converter<TT> getConverter(Class<TT> type) {
		Converter<TT> converter = (Converter<TT>) map.get(type);
		if (converter == null) {
			if (Enum.class.isAssignableFrom(type)) {
				return (Converter<TT>) enumConverter;
			}

			throw new RuntimeException("没有指定的转换器: " + type);
		}

		return converter;
	}
	
	@SuppressWarnings("unchecked")
	public static <TT> Object getConverter(Class<TT> type, Object o) {
		Converter<TT> converter = (Converter<TT>) map.get(type);
		if (converter == null) {
			throw new RuntimeException("没有指定的转换器: " + type);
		}
		return converter.convert(o);
	}

	protected abstract T convert(Object o);

	public T convert(Object o, Class<?> clazz) {
		return convert(o);
	}
}
