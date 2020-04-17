package com.wgz.frame.base;

import java.sql.Blob;

class ByteArrayConverter extends Converter<byte[]> {
	@Override
	public byte[] convert(Object o) {
		if (o == null)
			return null;

		if (o instanceof byte[])
			return (byte[]) o;

		if (o instanceof Blob) {
			Blob blob = (Blob) o;
			try {
				return blob.getBytes(1, (int) blob.length());
			} catch (Exception e) {
				throw new RuntimeException("不能转换 " + o.getClass().getSimpleName() + " 为 byte[]");
			}
		}

		throw new RuntimeException("不能转换 " + o.getClass().getSimpleName() + " 为 byte[]");
	}
}
