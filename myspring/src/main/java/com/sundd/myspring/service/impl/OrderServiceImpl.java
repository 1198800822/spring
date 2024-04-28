package com.sundd.myspring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sundd.myspring.mapper.OrderMapper;
import com.sundd.myspring.pojo.Order;
import com.sundd.myspring.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper mapper;
	
	@Override
	public Order get(int id) {
		// TODO Auto-generated method stub
		return mapper.get(id);
	}

}
