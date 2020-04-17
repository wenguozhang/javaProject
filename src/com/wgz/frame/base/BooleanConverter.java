package com.wgz.frame.base;

class BooleanConverter extends Converter<Boolean> {
	private boolean nullable;

	public BooleanConverter(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public Boolean convert(Object o) {
		if (o == null)
			return nullable ? null : false;

		if (o instanceof Boolean)
			return (Boolean) o;

		if (o instanceof Number)
			return 0 != ((Number) o).intValue();

		String s = o.toString();
		if ("true".equalsIgnoreCase(s) || "t".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s)
				|| "y".equalsIgnoreCase(s))
			return true;
		if ("false".equalsIgnoreCase(s) || "f".equalsIgnoreCase(s) || "no".equalsIgnoreCase(s)
				|| "n".equalsIgnoreCase(s))
			return false;

		throw new RuntimeException("不能转换 '" + o + "' 为Boolean类型");
	}
}
