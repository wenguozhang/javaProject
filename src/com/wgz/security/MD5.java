package com.wgz.security;

import java.security.MessageDigest;//Java类库已经支持MD5消息摘要

public class MD5 {
	private MD5() {
		System.out.println("采用MD5消息摘要算法");
	}

	public static String getMD5Code(String message) {
		String md5Str = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			// message.getBytes()方法是得到一个操作系统默认的编码格式的字节数组。不同的操作系统以及不同的编码格式会返回不同长度的byte型数组。
			// md.digest()方法获得密文完成哈希计算，产生128位的二进制串，并且8位一组分成16个byte类型的数值。
			byte[] md5Bytes = md.digest(message.getBytes());

			// 将16个数值都转换成两位16进制的数(161->A1,1->01)，因此消息摘要完成后，md5Str是长度为32位的字符串
			md5Str = BytesToHex(md5Bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return md5Str;
	}

	private final static byte[] byteArray = "你好123asd".getBytes();

	/**
	 * @Description: 字节数组转十六进制字符串方法1
	 */
	public static String BytesToHex(byte[] bytes) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			// 如果数值小于16且大于0，如3，将其转换为03
			if (bytes[i] >= 0 && bytes[i] <= 15)
				s.append("0");

			// bytes[i] & 0xFF保证了如果bytes[I]是负数，那么结果仍为两位16进制数;
			// toHexString()用于将参数以16进制的字符串形式返回，例如toHexString(241)返回值是字符串f1
			s.append(Integer.toHexString(bytes[i] & 0xFF));
		}
		return s.toString();
	}
	
	/**
	 * @Description: 字节数组转十六进制字符串方法2
	 */
	@Deprecated
	public static String byteArrToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = (char) bytes[v >>> 4];
			hexChars[j * 2 + 1] = (char) bytes[v & 0x0F];
		}
		return new String(hexChars);
	}

	/**
	 * @Description: 字节数组转十六进制字符串方法3
	 */
	public static String bytesToHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 十六进制字符串转换成字节数组方法1
	 * 
	 * @param hexString
	 * @return String
	 */
	public static String hexString2Bytes(String hexStr) {

		char[] hexs = hexStr.toCharArray();
		byte[] bytes = new byte[hexStr.length() / 2];
		int n;
		for (int i = 0; i < bytes.length; i++) {
			n = hexStr.indexOf(hexs[2 * i]) * 16;
			n += hexStr.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}
	
	 /**
	   * 把16进制字符串转换成字节数组方法2
	   * @param hexString
	   * @return byte[]
	   */
	  public static byte[] hexStringToByte(String hex) {
	   int len = (hex.length() / 2);
	   byte[] result = new byte[len];
	   char[] achar = hex.toCharArray();
	   for (int i = 0; i < len; i++) {
	    int pos = i * 2;
	    result[i] = (byte) (hex.indexOf(achar[pos]) << 4 | hex.indexOf(achar[pos + 1]));
	   }
	   return result;
	  }
	  
	/**
	 * 十六进制字符串转换字节数组方法3：
	 * 
	 * @param HexString
	 * @return String
	 */
	public static byte[] hexStringtoBytes(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return baKeyword;
	}

	public static void main(String args[]) {
		System.out.println(BytesToHex(byteArray));
		System.out.println(bytesToHexString(byteArray));
		System.out.println(new String(hexString2Bytes(BytesToHex(byteArray))));
		System.out.println(new String(hexStringToByte(BytesToHex(byteArray))));
		System.out.println(new String(hexStringtoBytes(BytesToHex(byteArray))));
		
		System.out.println(new String(hexString2Bytes(bytesToHexString(byteArray))));
		System.out.println(new String(hexStringToByte(bytesToHexString(byteArray))));
		System.out.println(new String(hexStringtoBytes(bytesToHexString(byteArray))));
		
		System.out.println(new String(hexStringtoBytes(BytesToHex(byteArray))));
		System.out.println(new String(hexStringtoBytes(bytesToHexString(byteArray))));
		System.out.println(getMD5Code("123"));
	}
}