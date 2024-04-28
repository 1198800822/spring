package com.sundd.myspring.service;

import java.util.List;

import com.sundd.myspring.pojo.User;

public interface UserService {

	public int add(User user);
	public int addList(List<User> list);
	public int delete(int id);
	public int update(User user);
	public User get(int id);
	public User getBy(String name,String sex);
	public int addBatch(List<User> list);
	public List<User> getList(String param);
}
