package com.wgz.frame.base;

public class DoubleConverter extends Converter<Double> {
	private boolean nullable;

	public DoubleConverter(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public Double convert(Object o) {
		if (o == null)
			return nullable ? null : 0.0;

		if (o instanceof Number)
			return ((Number) o).doubleValue();

		return new Double(o.toString());
	}
}
