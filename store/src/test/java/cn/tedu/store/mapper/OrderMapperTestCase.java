package cn.tedu.store.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTestCase {

	@Autowired
	public OrderMapper mapper;
	
	@Test
	public void insertOrder() {
		Order order = new Order();
		order.setUid(8);
		order.setName("小李同学");
		System.err.println(order);
		Integer rows = mapper.insertOrder(order);
		System.err.println("rows=" + rows);
		System.err.println(order);
	}
	
	@Test
	public void insertOrderItem() {
		OrderItem orderItem = new OrderItem();
		orderItem.setOid(1);
		orderItem.setTitle("笔记本电脑");
		orderItem.setPrice(6555L);
		orderItem.setNum(3);
		Integer rows = mapper.insertOrderItem(orderItem);
		System.err.println("rows=" + rows);
	}
	
}






