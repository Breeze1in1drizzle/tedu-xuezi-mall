package cn.tedu.store.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTestCase {

	@Autowired
	public IOrderService service;
	
	@Test
	public void createOrder() {
		try {
			Integer uid = 8;
			String username = "系统管理员";
			Integer aid = 13;
			Integer[] cids = { 6,7,9 };
			Order order = service.createOrder(uid, username, aid, cids);
			System.err.println(order);
		} catch (ServiceException e) {
			System.err.println(e.getClass().getName());
			System.err.println(e.getMessage());
		}
	}
	
	
}






