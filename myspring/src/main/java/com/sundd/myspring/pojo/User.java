package com.sundd.myspring.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1102591714121016561L;
	private int id;
	private String name;
	private String sexx;
	private String date;
	private List<Order> orderList = new ArrayList<Order>();
	

	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSexx() {
		return sexx;
	}
	public void setSexx(String sexx) {
		this.sexx = sexx;
	}
	public List<Order> getOrderList() {
		return orderList;
	}
	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public User copy(User user){
		User copy = new User();
		copy.setDate(user.getDate());
		copy.setId(user.getId());
		copy.setName(user.getName());
		copy.setOrderList(user.getOrderList());
		copy.setSexx(user.getSexx());
		return copy; 
	}

}
