package com.sundd.myspring.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.sundd.myspring.pojo.User;

public interface UserMapper {
	
	public int add(User user);
	public int delete(int id);
	public int update(User user);
	public User get(@Param("bbb")int id);
	public User getBy(String name,String sex);
	public int addBatch(List<User> list);
	public List<User> getList(String param);

}
