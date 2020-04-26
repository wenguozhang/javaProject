package com.wgz.security;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


/**
 * @创建人 caiyonggang
 * @创建时间 2016年12月28日上午10:05:13
 * @说明 DES加密 本系统存储敏感数据进行加密存储的加密方法
 * @备注
 */
public class DESUtil {
	
	public static void main(String[] args) throws Exception {
//		System.out.println(new String("你好".getBytes("UTF-8"),"UTF-8"));
//		byte[] encryptedData = "你好".getBytes("UTF-8");
//		System.out.println(new String(new String(encryptedData,"UTF-8").getBytes("UTF-8")));
		String miwen = encryptDES("你好","12345678");  
		System.out.println(decryptDES(miwen,"12345678"));
 
//		DESKeySpec dks = new DESKeySpec(("12345678").getBytes());
//		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
//		Key secretKey = keyFactory.generateSecret(dks);
//		Cipher cipher = Cipher.getInstance("DES");
//		cipher.init(Cipher.ENCRYPT_MODE, secretKey,  new SecureRandom());
//		byte[] encryptedData = cipher.doFinal("你好".getBytes("UTF-8"));
//		cipher.init(Cipher.DECRYPT_MODE, secretKey,  new SecureRandom());
//		System.out.println(encryptedData.equals(hexStringtoBytes(bytesToHexString(encryptedData))));
//		System.out.println(encryptedData.equals(new String(encryptedData).getBytes()));
////		byte decryptedData[] = cipher.doFinal(encryptedData);
//		byte decryptedData[] = cipher.doFinal(hexStringtoBytes(bytesToHexString(encryptedData)));
////		byte decryptedData[] = cipher.doFinal((new String(encryptedData)).getBytes());
//		System.out.println(new String(decryptedData,"UTF-8"));
	}
	
	/**
	 * @创建人 caiyonggang
	 * @创建时间 2016年12月28日上午10:08:16
	 * @说明 指定加密的key的DES加密重载方法
	 * @备注
	 * @param encryptString
	 * @param encryptKey
	 * @return
	 */
	public static String encryptDES(String encryptString, String encryptKey) {
		try {
			
			DESKeySpec dks = new DESKeySpec((encryptKey).getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey,  new SecureRandom());
			byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
			return new String(Base64.getEncoder().encode(encryptedData));
//			return bytesToHexString(encryptedData);
//			return new String(encryptedData,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * @创建人 caiyonggang
	 * @创建时间 2016年12月28日上午10:08:47
	 * @说明 指定key的DES解密重载方法
	 * @备注
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 */
	public static String decryptDES(String decryptString, String decryptKey) {
		try {
			DESKeySpec dks = new DESKeySpec((decryptKey).getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			Key secretKey = keyFactory.generateSecret(dks);
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey,  new SecureRandom());
			byte decryptedData[] = cipher.doFinal(Base64.getDecoder().decode(decryptString));
//			byte decryptedData[] = cipher.doFinal(hexStringtoBytes(decryptString));
//			byte decryptedData[] = cipher.doFinal(decryptString.getBytes("UTF-8"));
			return new String(decryptedData,"UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
}
