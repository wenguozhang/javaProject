package com.wgz.frame.utils;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.wgz.frame.options.Options;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

public class RedisUtil {
	private boolean sentinel;
	
	private JedisPool pool1;
	private String password;
	
	private Integer timeout;
	private Integer connect;
	
	private JedisSentinelPool pool2;
	
	private static String scriptIAmAlive;
	
	private void init(Options options) throws Exception {
		sentinel = options.get("redis.sentinel", false);
		timeout = options.get("redis.timeout", 2000);
		connect = options.get("redis.max.conn", 8);
		//org.apache.commons.pool2.impl
		GenericObjectPoolConfig config = new GenericObjectPoolConfig();
		config.setMaxTotal(connect);
		
		if(!sentinel) {
			String host = options.get("redis.home");
			int port = options.get("redis.port");
			password = options.get("redis.password", null);
			
			pool1 = new JedisPool(config, host, port, timeout);
		} else {
			String master = options.get("redis.sentinel.master");
			Set<String> set = new HashSet<>();
			for (int i = 0; i< 30; i++) {
				String s = options.get("redis.sentinel." + i, null);
				if (s == null)
					continue;
				set.add(s);
			}
			password = options.get("redis.sentinel.password", null);
			pool2 = new JedisSentinelPool(master, set, config, timeout, password);
		}
	}
	

	public Jedis getJedis() throws Exception {
		Options options1 = new Options();
		init(options1);
		
		if(!sentinel) {
			Jedis jedis = pool1.getResource();
			if (jedis != null && password != null) {
				jedis.auth(password);
			}
			return jedis;
		} else {
			return pool2.getResource();
		}
	}
	
	/** 加载lua脚本到redis服务器*/
	public String loadLuaScript(String file) throws Exception {
		try (InputStream is = getClass().getResourceAsStream(file)) {
			byte[] buffer = new byte[4096];
			int read = IOUtils.read(is, buffer);//org.apache.common.io.util
			
			String text = new String(buffer, 0, read, "UTF-8");
			try (Jedis jedis = getJedis()) {
				return jedis.scriptLoad(text);
			}
		} catch (Exception e) {
			throw new RuntimeException("加载redis脚本错误：" + file, e);
		}
	}
	
	public void close(Jedis jedis) {
		if (jedis != null)
			jedis.close();
	}
	
	public static void main(String[] args) throws Exception {
		RedisUtil redisUtil = new RedisUtil();
		scriptIAmAlive = redisUtil.loadLuaScript("i_an_alive.lua");
		
		try (Jedis jedis = redisUtil.getJedis()) {
			Object r = jedis .evalsha(scriptIAmAlive, 2, "ServerId", "ARGV[1]", "ARGV[2]");
			
			boolean f = jedis.exists("PP");
			if (!f) {
				jedis.set("PP", "123");
				jedis.incrByFloat("PP", 456);
				String s = jedis.get("PP");
			}
		}
	}
}
