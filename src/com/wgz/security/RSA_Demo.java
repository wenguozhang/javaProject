package com.wgz.security;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/*
 * SHA1即安全哈希算法（Secure Hash Algorithm），用于签名；RSA是目前最有影响力的公钥加密算法。
说到这就的提到公钥和私钥：公钥、私钥分居客户端和服务器端，分别用于加密和解密。同时，私钥还用于签名，公钥还用于验证签名。
下边是一个发送端的签名及对签名的加密，接收端的解密与验证签名的应用：
* SHA1WithRSA:用SHA算法进行签名，用RSA算法进行加密
* 发送端的签名及对签名的加密，接收端的解密与验证签名
*/
public class RSA_Demo {
	
	public static void main(String[] args) {
		new RSA_Demo();
	}
	
	RSA_Demo() {
		init();
	}

	private void init() {
		prepare();
		doSenderWork();
		doReceiverWork();
	}

	// share by sender and receiver
	Signature sign = null;
	// belong to sender,it visible to sender and receiver
	PublicKey publicKey = null;
	// belong to sender,it is only visible to sender
	PrivateKey privateKey = null;;

	private void prepare() {
		KeyPairGenerator keyGen = null;
		try {
			// 实例化一个RSA算法的公钥/私钥对生成器
			keyGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int keysize = 1024;
		// 设置公钥/私钥对的长度
		keyGen.initialize(keysize);
		// 生成一个RSA算法的公钥/私钥
		KeyPair keyPair = keyGen.generateKeyPair();
		privateKey = keyPair.getPrivate();
		publicKey = keyPair.getPublic();
		try {
			//实例化一个用SHA算法进行散列，用RSA算法进行加密的Signature.
			sign = Signature.getInstance("SHA1WithRSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	void doSenderWork() {
		String words = "This is robin.How are you?";
		Message msg = new Message(words.getBytes());
		try {
			//设置加密散列码用的私钥
			sign.initSign(privateKey);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			//设置散列算法的输入
			sign.update(msg.getBody());
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte sn[] = null;
		try {
			//进行散列，对产生的散列码进行加密并返回
			sn = sign.sign();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//把加密后散列（即签名）加到消息中
		msg.setSignature(sn);
		//发送消息
		sendMsg(msg);
	}

	Message sendingMsg;

	void sendMsg(Message sendMsg) {
		sendingMsg = sendMsg;
		System.out.println("sending Message:"+sendMsg);
	}

	void doReceiverWork() {
		//收到消息
		Message msg = getReceivedMsg();
		try {
			//设置解密散列码用的公钥。
			sign.initVerify(publicKey);
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			//设置散列算法的输入
			sign.update(msg.getBody());
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			/*
			 * 进行散列计算，比较计算所得散列码是否和解密的散列码是否一致。 一致则验证成功，否则失败
			 */
			if (sign.verify(msg.getSignature())) {
				System.out.println("数字签名验证成功！");
			} else {
				System.out.println("数字签名验证失败！");
			}
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	Message getReceivedMsg() {
		System.out.println("receiving Message");
		return sendingMsg;
	}
}

class Message {
	private byte[] body;
	private byte[] signature;

	Message(byte data[]) {
		body = data;
	}

	byte[] getBody() {
		return body;
	}

	byte[] getSignature() {
		return signature;
	}

	void setSignature(byte data[]) {
		signature = data;
	}
	
	public String toString() {
		return new String(body) +"--------------\r\n"+ new String(signature);
	}
}
