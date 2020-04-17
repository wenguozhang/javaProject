package com.wgz.frame.base;

class FloatConverter extends Converter<Float> {
	private boolean nullable;

	public FloatConverter(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public Float convert(Object o) {
		if (o == null)
			return nullable ? null : 0.0f;

		if (o instanceof Number)
			return ((Number) o).floatValue();

		return new Float(o.toString());
	}
}
