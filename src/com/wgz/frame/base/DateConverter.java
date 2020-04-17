package com.wgz.frame.base;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

import com.wgz.frame.utils.DateUtil;


class DateConverter extends Converter<Date> {
	// private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd
	// hh:mm:ss");

	@Override
	public Date convert(Object o) {
		if (o == null)
			return null;

		if (o instanceof Date)
			return (Date) o;

		String s = o.toString();

		Class<?> clazz = o.getClass();
		if (clazz.getName().equals("oracle.sql.TIMESTAMP")) {
			try {
				Method method = clazz.getMethod("timestampValue", (Class<?>[]) null);

				return (Date) method.invoke(o, (Object[]) null);
			} catch (Exception e) {
				throw new RuntimeException("不能转换 '" + s + "' 为日期类型", e);
			}
		}

		try {
			return DateUtil.getDate(s, DateUtil.SDF_DATETIME_19);
		} catch (ParseException e) {
			throw new RuntimeException("不能转换 '" + s + "' 为日期类型", e);
		}
	}
}
