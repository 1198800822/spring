package com.sundd.myspring.service.impl;

import java.util.List;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.sundd.myspring.mapper.UserMapper;
import com.sundd.myspring.pojo.User;
import com.sundd.myspring.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	@Override
	public int add(User user) {
		// TODO Auto-generated method stub
		return mapper.add(user);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return mapper.delete(id);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return mapper.update(user);
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		mapper.get(id);
		mapper.get(id);

		return mapper.get(id);
	}

	@Override
	public int addBatch(List<User> list) {
		// TODO Auto-generated method stub
		return mapper.addBatch(list);
	}

	@Override
	public List<User> getList(String param) {
		// TODO Auto-generated method stub
		return mapper.getList(param);	
	}

	@Override
	public User getBy(String name, String sex) {
		// TODO Auto-generated method stub
		return mapper.getBy(name, sex);
	}

	@Override
	public int addList(List<User> list) {
		// TODO Auto-generated method stub
		try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			for (User user : list) {
				mapper.add(user);
			}
			sqlSession.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
