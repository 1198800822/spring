package com.sundd.myspring.bak;

import java.util.Properties;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisFactory_bak {
	private static Logger log = Logger.getLogger(JedisFactory_bak.class);

	// 用于存放当前线程的jedis连接是否中断过
	private static final ThreadLocal<Boolean> local = new ThreadLocal<Boolean>();

	private static Properties prop;
	private static JedisPool jedisPool;

	public static void ini(Properties properties) {
		prop = properties;

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(parseInt(prop.get("jedis.pool.maxtotal"))); // 最大连接数
		poolConfig.setMaxIdle(parseInt(prop.get("jedis.pool.maxidle"))); // 最大空闲连接数
		poolConfig.setMinIdle(parseInt(prop.get("jedis.pool.minidle"))); // 最小空闲连接数
		poolConfig.setMaxWaitMillis(parseInt(prop.get("jedis.pool.MaxWaitMillis"))); // 获取连接时的最大等待毫秒数
		poolConfig.setTestOnBorrow(true); // 获取连接时是否检查空闲连接
		// 配置,ip,端口,超时,密码
		jedisPool = new JedisPool(poolConfig, prop.get("jedis.server.ip").toString(),
				parseInt(prop.get("jedis.server.port")), parseInt(prop.get("jedis.pool.timeout")),
				prop.get("jedis.server.auth").toString());
	}

	public static Jedis getJedis() {
		Jedis jedis = null;

		Boolean isBreak = local.get();
		if (isBreak == null) {
			isBreak = new Boolean(false); // 第一次获取连接,默认没异常过
		}
		boolean exit = false;
		while (!exit) {
			try {
				jedis = jedisPool.getResource();
				if (jedis != null && isBreak) {
					log.info("Jedis链接恢复,可以正常使用");
					isBreak = false;
					local.set(isBreak);
				}
				exit = true;
			} catch (Exception e) {
				log.info("Jedis链接异常:" + e.getMessage());
				isBreak = true;
				local.set(isBreak);
				try {
					Thread.sleep(10 * 1000);
				} catch (InterruptedException e1) {
					return null;
				}
			}
		}
		return jedis;
	}

	public static String getProperties(String key) {
		return prop.getProperty(key);
	}

	private static int parseInt(Object obj) {
		return Integer.parseInt(obj.toString());
	}

}
