package com.sundd.myspring.jedis;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.sundd.myspring.App;
import com.sundd.myspring.pojo.User;

@Component
public class UserHandler {
	private static Logger log = Logger.getLogger(UserHandler.class);

	private final String JEDIS_USER_LIST = "jedis.list.user";
	@Autowired
	@Qualifier(value = "jedisProp")
	private Properties jedisProp;

	public boolean sendUser(User user) {
		boolean result = JedisUtil.sendObject(jedisProp.getProperty(JEDIS_USER_LIST), user);
		if (result) {
			log.info("发送User[id:" + user.getId() + "|name:" + user.getName() + "]到Jedis成功!");
		} else {
			log.error("发送User到Jedis失败");
		}
		return result;
	}

	public User receiveUser() {
		log.info("开始从redis取User信息");
		User user = (User) JedisUtil.receiveObject(jedisProp.getProperty(JEDIS_USER_LIST));
		if (user != null) {
			log.info("取得User信息:" + user.getId() + ":" + user.getName());
			return user;
		} else {
			log.info("没有从Jedis取得User信息");
			return null;
		}
	}

}
