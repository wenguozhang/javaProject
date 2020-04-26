package com.wgz.security;
 
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
 
public class testdsa {
	public static void main(String[] args) throws java.security.NoSuchAlgorithmException,
			java.lang.Exception {
		testdsa my = new testdsa();
		my.run();
	}
 
	public void run() {
			// 数字签名生成密钥
			// 第一步生成密钥对 , 如果已经生成过 , 本过程就可以跳过 ,
			// 对用户来讲 myprikey.dat 要保存在本地
			// 而 mypubkey.dat 给发布给其它用户
		if ((new java.io.File("myprikey.dat")).exists() == false) {
			if (generatekey() == false) {
				System.out.println("生成密钥对败");
				return;
			}
			;
		}
			// 第二步 , 此用户
			// 从文件中读入私钥 , 对一个字符串进行签名后保存在一个文件 (myinfo.dat) 中
			// 并且再把 myinfo.dat 发送出去
			// 为了方便数字签名也放进了 myifno.dat 文件中 , 当然也可分别发送
		try {
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(
					new java.io.FileInputStream("myprikey.dat"));
			PrivateKey myprikey = (PrivateKey) in.readObject();
			in.close();
			String myinfo = "这是我的信息"; // 要签名的信息
			// 用私钥对信息生成数字签名
			java.security.Signature signet = java.security.Signature.getInstance("DSA");
			signet.initSign(myprikey);
			signet.update(myinfo.getBytes());
			byte[] signed = signet.sign(); // 对信息的数字签名
			System.out.println("signed( 签名内容 )=" + byte2hex(signed));
			// 把信息和数字签名保存在一个文件中
			java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream("myinfo.dat"));
			out.writeObject(myinfo);
			out.writeObject(signed);
			out.close();
			System.out.println("签名并生成文件成功");
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			System.out.println("签名并生成文件失败");
		}
			// 第三步
			// 其他人通过公共方式得到此户的公钥和文件
			// 其他人用此户的公钥 , 对文件进行检查 , 如果成功说明是此用户发布的信息 .
		try {
			java.io.ObjectInputStream in = new java.io.ObjectInputStream(
					new java.io.FileInputStream("mypubkey.dat"));
			PublicKey pubkey = (PublicKey) in.readObject();
			in.close();
			System.out.println(pubkey.getFormat());
			in = new java.io.ObjectInputStream(new java.io.FileInputStream("myinfo.dat"));
			String info = (String) in.readObject();
			byte[] signed = (byte[]) in.readObject();
			in.close();
			java.security.Signature signetcheck = java.security.Signature.getInstance("DSA");
			signetcheck.initVerify(pubkey);
			signetcheck.update(info.getBytes());
			if (signetcheck.verify(signed)) {
				System.out.println("info=" + info);
				System.out.println("签名正常");
			} else System.out.println("非签名正常");
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
	}
	// 生成一对文件 myprikey.dat 和 mypubkey.dat--- 私钥和公钥 ,
	// 公钥要用户发送 ( 文件 , 网络等方法 ) 给其它用户 , 私钥保存在本地
	public boolean generatekey() {
		try {
			java.security.KeyPairGenerator keygen =
					java.security.KeyPairGenerator.getInstance("DSA");
			keygen.initialize(512);
			KeyPair keys = keygen.genKeyPair();// 生成密钥组
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();
			java.io.ObjectOutputStream out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream("myprikey.dat"));
			out.writeObject(prikey);
			out.close();
			System.out.println("写入对象 prikeys ok");
			out = new java.io.ObjectOutputStream(
					new java.io.FileOutputStream("mypubkey.dat"));
			out.writeObject(pubkey);
			out.close();
			System.out.println("写入对象 pubkeys ok");
			System.out.println("生成密钥对成功");
			return true;
		} catch (java.lang.Exception e) {
			e.printStackTrace();
			System.out.println("生成密钥对失败");
			return false;
		}
	}
 
	public String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) hs = hs + "0" + stmp;
			else hs = hs + stmp;
			if (n < b.length - 1) hs = hs + ":";
		}
		return hs.toUpperCase();
	}
}