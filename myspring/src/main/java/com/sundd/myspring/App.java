package com.sundd.myspring;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.sun.xml.internal.ws.resources.TubelineassemblyMessages;
import com.sundd.myspring.jedis.UserHandler;
import com.sundd.myspring.pojo.Order;
import com.sundd.myspring.pojo.User;
import com.sundd.myspring.service.OrderService;
import com.sundd.myspring.service.UserService;
import com.sundd.myspring.util.JSONUtils;
import com.sundd.myspring.util.SunddUtils;

import net.sf.ehcache.Cache;

/**
 * Hello world!
 *
 */
@Component
public class App {
	private static final String APPLICATION_PATH = "classpath:applicationContext.xml";
	private static ApplicationContext ac;

	public static void main(String[] args) throws Exception {
		App app = new App();
		app.sysConfigIni(); // 系统初始化

		app.jedisTest();
//		app.mybatisTest(); 
	}

	public void sysConfigIni() {
		// spring初始化
		ac = new ClassPathXmlApplicationContext(App.APPLICATION_PATH);
	}

	public void jedisTest() {
		UserHandler jedisHandler = (UserHandler) ac.getBean("userHandler");
		
		int flag = 0; 	//发送
		flag = 1;		//接收
		if (flag == 0) {
			// 添加对象到Jedis
			while (true) {
				User user = new User();
				user.setName(SunddUtils.getRandomChinese(3));
				jedisHandler.sendUser(user);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		} else {
			// 从jeids取对象,并保存到数据库
			while (true) {
				User user = jedisHandler.receiveUser();
				if (user != null) {
					System.out.println("接收到:"+user.getName()+".开始处理...");
					UserService userService = (UserService) ac.getBean("userServiceImpl");
					userService.add(user);
					System.out.println("添加:id:"+user.getId()+"|name:"+user.getName());
				}
			}
		}

	}

	public void mybatisTest() throws Exception {
		UserService userService = (UserService) ac.getBean("userServiceImpl");
		OrderService orderService = (OrderService) ac.getBean("orderServiceImpl");

		int id = 3;
		int returnValue = 0;

		// User user = new User();
		// user.setName("老中");
		// returnValue = userService.add(user);
		// System.out.println("添加:" + returnValue);
		//
		// returnValue = userService.delete(id);
		// System.out.println("删除:"+returnValue);
		//
		// user.setId(++id);
		// user.setName("老彭");
		// returnValue = userService.update(user);
		// System.out.println("修改:"+returnValue);

		// id = 30;
		// userService.get(id);
		// User result = userService.get(id);
		// System.out.println("查询:" + result.getName() + result.getSexx());
		// result = userService.get(id);
		// System.out.println("查询:" + result.getName() + result.getSexx());
		//
		// String name = "彭";
		// String sex = "男";
		// User a = userService.getBy(name, sex);
		// System.out.println(a.getId() + a.getName());
		//

		// List<User> list = new ArrayList<User>();
		// for (int i = 0; i < 10000; i++) {
		// User user = new User();
		// user.setName("山本" + i);
		// user.setSexx("男");
		// list.add(user);
		// }
		// StopWatch timer = new StopWatch();
		// timer.start();
		//// returnValue = userService.addBatch(list);
		// userService.addList(list);
		// timer.stop();
		// System.out.println(timer.getTotalTimeMillis());
		// System.out.println("批量添加:" + returnValue);


		/*
		 * 关联查询开始
		 */
		// 一对一
		// Order order = orderService.get(1);
		// System.out.println("订单查询:"+order.getName()+"||||||"+order.getUser().toString());
		//
		// //一对多
		// User user = userService.get(4);
		// System.out.println(user.getDate());
		// for(Order obj:user.getOrderList()){
		// System.out.println(obj.getId()+obj.getUser().toString());
		// }
	}

}
