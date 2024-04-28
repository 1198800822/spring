package com.sundd.myspring.cache;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sundd.myspring.mapper.UserMapper;
import com.sundd.myspring.pojo.User;
import com.sundd.myspring.service.UserService;

import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

@Component
public class UserCacheManager {
	private static Logger log = Logger.getLogger(UserCacheManager.class);

	@Autowired
	@Qualifier(value = "userCache")
	private Ehcache cache;
	@Autowired
	private UserService userService;

	public void reload() {
//		clear();

		List<User> list = userService.getList("");
		for (User user : list) {
			add(String.valueOf(user.getId()), user);
		}

		int listSize = list.size();
		int cacheSize = cache.getSize();
		int sub = cacheSize - listSize;
		if (sub == 0) {
			log.info("加载用户信息:" + listSize + "条");
		} else {
			log.error("用户缓存异常!缓存数" + cache.getSize() + "条,数据库读取:" + listSize + "条");
		}

	}

	public boolean add(String key, Object value) {
		Element ele = new Element(key, value);
		cache.put(ele);
		return true;
	}

	public Object get(String key) {
		Element ele = cache.get(key);
		return ele.getObjectValue();
	}

	public void clear() {
		cache.removeAll();
	}

}
