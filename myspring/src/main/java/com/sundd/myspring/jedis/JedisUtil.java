package com.sundd.myspring.jedis;

import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sundd.myspring.util.SunddUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class JedisUtil {
	private static Logger log = Logger.getLogger(JedisUtil.class);
	
	private static JedisPool jedisPool;

	public static boolean sendObject(String listName, Object obj) {
		try (Jedis jedis = getJedis()){
			jedis.rpush(listName.getBytes(), SunddUtils.objSer(obj));
		} catch (Exception e) {
			log.error("发送任务到Jedis失败:" + e.getMessage());
			return false;
		} 
		return true;
	}

	public static Object receiveObject(String listName) {
		Object obj = null;
		try(Jedis jedis = getJedis()) {
			/**
			 * 该方法返回一个两位的list,list[0]为该元素所在队列名(无用),list[1]为该元素的序列化数组 10为阻塞超时时间
			 */
			List<byte[]> list = jedis.blpop(10, listName.getBytes());
			if (list != null && list.size() >= 2) {
				obj = SunddUtils.objDeser(list.get(1));
			}
		} catch (Exception e) {
			log.info("Jedis连接异常断开, 操作终止");
			return null;
		} 
		return obj;
	}
	
	public static Jedis getJedis() {
		Jedis jedis = null;

		Boolean isBreak = JedisLinkThreadLocal.get();
		if (isBreak == null) {
			isBreak = new Boolean(false); // 第一次获取连接,默认没异常过
		}
		boolean exit = false;
		while (!exit) {
			try {
				jedis = jedisPool.getResource();
				if(jedis != null){
					exit = true;
					if(isBreak){
						log.info("Jedis链接恢复,可以正常使用");
						JedisLinkThreadLocal.set(new Boolean(false));						
					}
				}else{
					log.error("Jedis链接异常");
					JedisLinkThreadLocal.set(new Boolean(true));
					try {
						Thread.sleep(1 * 1000);
					} catch (InterruptedException e) {
						
					}
					continue;
				}
			} catch (Exception e) {
				log.error("Jedis链接异常:" + e.getMessage());
				JedisLinkThreadLocal.set(new Boolean(true));
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e1) {
					
				}
				continue;
			}
		}
		return jedis;
	}

	@Autowired
	public void setJedisPool(JedisPool jedisPool) {
		JedisUtil.jedisPool = jedisPool;
	}
}
