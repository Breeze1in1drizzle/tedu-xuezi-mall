package cn.tedu.store.service;

import cn.tedu.store.entity.Order;
import cn.tedu.store.service.ex.InsertException;

/**
 * 处理订单和订单商品数据的业务层接口
 */
public interface IOrderService {

	/**
	 * 创建订单
	 * @param uid 当前登录的用户的id
	 * @param username 当前登录的用户的用户名
	 * @param aid 用户选择的收货地址的id
	 * @param cids 用户选择即将购买的购物车数据的id
	 * @return 成功创建的订单对象
	 * @throws InsertException 插入数据异常 
	 */
	Order createOrder(Integer uid, String username, 
			Integer aid, Integer[] cids) 
					throws InsertException;
	
}


