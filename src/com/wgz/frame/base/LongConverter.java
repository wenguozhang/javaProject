package com.wgz.frame.base;

public class LongConverter extends Converter<Long> {
	private boolean nullable;

	public LongConverter(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public Long convert(Object o) {
		if (o == null)
			return nullable ? null : 0L;

		if (o instanceof Number)
			return ((Number) o).longValue();

		return new Long(o.toString());
	}
}
