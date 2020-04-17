package com.wgz.frame.base;

class ShortConverter extends Converter<Short> {
	private boolean nullable;

	public ShortConverter(boolean nullable) {
		this.nullable = nullable;
	}

	@Override
	public Short convert(Object o) {
		if (o == null)
			return nullable ? null : (short) 0;

		if (o instanceof Number)
			return ((Number) o).shortValue();

		if (o instanceof Boolean)
			return ((Boolean) o).booleanValue() ? (short) 1 : (short) 0;

		return new Short(o.toString());
	}
}
