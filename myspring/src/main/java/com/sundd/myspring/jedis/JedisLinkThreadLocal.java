package com.sundd.myspring.jedis;

public class JedisLinkThreadLocal {
	private JedisLinkThreadLocal() {
	}
	// 用于存放当前线程的jedis连接是否中断过
	private static final ThreadLocal<Boolean> local = new ThreadLocal<Boolean>();

	public static Boolean get() {
		return local.get();
	}

	public static void set(Boolean value) {
		local.set(value);
	}

}
