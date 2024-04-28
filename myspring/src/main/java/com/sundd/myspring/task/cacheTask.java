package com.sundd.myspring.task;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sundd.myspring.cache.UserCacheManager;


@Component
public class cacheTask {
	private static Logger log = Logger.getLogger(cacheTask.class);
	
	@Autowired
	private UserCacheManager userCacheManager;

	/**
	 * 每10s刷新一次用户信息
	 */
	@Scheduled(cron = "0/30 * * * * ?")
	public void loadUserCache() {
		log.info("定时任务: 开始刷新用户缓存");
		
		userCacheManager.reload();
		
		log.info("用户缓存刷新结束");
	}

}
