package com.wgz.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 * 
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * 
 */
public class DigestUtils {

	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();

	/**
	 * 对输入字符串进行sha1散列.
	 */
	public static byte[] sha1(byte[] input) {
		return digest(input, SHA1, null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) {
		return digest(input, SHA1, salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) {
		return digest(input, SHA1, salt, iterations);
	}
	
	/**
	 * 对输入字符串进行md5.
	 */
	public static byte[] md5(byte[] input) {
		return digest(input, MD5, null, 1);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);

			if (salt != null) {
				digest.update(salt);
			}

			byte[] result = digest.digest(input);

			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 生成随机的Byte[]作为salt.
	 * 
	 * @param numBytes byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * 对文件进行md5散列.
	 */
	public static byte[] md5(InputStream input) throws IOException {
		return digest(input, MD5);
	}

	/**
	 * 对文件进行sha1散列.
	 */
	public static byte[] sha1(InputStream input) throws IOException {
		return digest(input, SHA1);
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read(buffer, 0, bufferLength);

			while (read > -1) {
				messageDigest.update(buffer, 0, read);
				read = input.read(buffer, 0, bufferLength);
			}

			return messageDigest.digest();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String BytesToHex(byte[] bytes){
	    StringBuffer s = new StringBuffer();
	    for(int i=0;i<bytes.length;i++){
	      //如果数值小于16且大于0，如3，将其转换为03
	      if(bytes[i]>=0 && bytes[i] <= 15)
	        s.append("0");
	 
	      //bytes[i] & 0xFF保证了如果bytes[I]是负数，那么结果仍为两位16进制数;
	      //toHexString()用于将参数以16进制的字符串形式返回，例如toHexString(241)返回值是字符串f1
	      s.append(Integer.toHexString(bytes[i] & 0xFF));
	    }
	    return s.toString();
	  }
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
        
		System.out.println(md5("123".getBytes()));
		System.out.println(md5("123".getBytes()).toString());
        System.out.println(BytesToHex(md5("123".getBytes())));
        System.out.println(md5("123".getBytes()));
        System.out.println(md5("123".getBytes()).toString());
        System.out.println(BytesToHex(md5("123".getBytes())));
	}

}
