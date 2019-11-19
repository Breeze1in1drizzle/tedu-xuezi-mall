package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.Order;
import cn.tedu.store.entity.OrderItem;
import cn.tedu.store.mapper.OrderMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.ICartService;
import cn.tedu.store.service.IOrderService;
import cn.tedu.store.service.ex.AccessDeniedException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.vo.CartVO;

/**
 * 处理订单和订单商品数据的业务层实现类
 */
@Service
public class OrderServiceImpl implements IOrderService {
	
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private IAddressService addressService;
	@Autowired
	private ICartService cartService;

	@Override
	public Order createOrder(Integer uid, String username, Integer aid, Integer[] cids) throws InsertException {
		// 创建当前时间对象now
		Date now = new Date();

		// 调用cartService方法根据cids获取数据：List<CartVO> getByCids(Integer[] cids);
		List<CartVO> carts = cartService.getByCids(cids);
		// 遍历计算商品总价
		Long price = 0L;
		for (CartVO cartVO : carts) {
			price += cartVO.getPrice() * cartVO.getNum();
		}

		// 创建订单数据对象：new Order()
		Order order = new Order();
		// 封装订单数据的属性：uid
		order.setUid(uid);
		// 调用addressService获取收货地址数据：getByAid(Integer aid)
		Address address = addressService.getByAid(aid);
		// 封装订单数据的属性：name, phone, address
		if (address == null) {
			throw new AddressNotFoundException(
				"创建订单失败！选择的收货地址不存在！");
		}
		if (!address.getUid().equals(uid)) {
			throw new AccessDeniedException(
				"创建订单失败！收货地址数据归属有误！");
		}
		order.setName(address.getName());
		order.setPhone(address.getPhone());
		order.setAddress(address.getDistrict() + " " + address.getAddress());
		// 封装订单数据的属性：status:0
		order.setStatus(0);
		// 封装订单数据的属性：price
		order.setPrice(price);
		// 封装订单数据的属性：orderTime:now
		order.setOrderTime(now);
		// 封装订单数据的属性：payTime:null
		order.setPayTime(null);
		// 封装4项日志属性
		order.setCreatedUser(username);
		order.setCreatedTime(now);
		order.setModifiedUser(username);
		order.setModifiedTime(now);
		// 插入订单数据：insertOrder(Order order)
		insertOrder(order);

		// 遍历以上查询结果
		for (CartVO cart : carts) {
			// -- 创建订单商品数据对象：new OrderItem()
			OrderItem item = new OrderItem();
			// -- 封装订单商品数据的属性：oid
			item.setOid(order.getOid());
			// -- 封装订单商品数据的属性：gid,title,image,price,num
			item.setGid(cart.getGid());
			item.setTitle(cart.getTitle());
			item.setImage(cart.getImage());
			item.setPrice(cart.getPrice());
			item.setNum(cart.getNum());
			// -- 封装4项日志属性
			item.setCreatedUser(username);
			item.setCreatedTime(now);
			item.setModifiedUser(username);
			item.setModifiedTime(now);
			// -- 插入订单商品数据：insertOrderItem(OrderItem orderItem)
			insertOrderItem(item);
		}
		
		return order;
	}
	
	/**
	 * 插入订单数据
	 * @param order 订单数据
	 */
	private void insertOrder(Order order) {
		Integer rows = orderMapper.insertOrder(order);
		if (rows != 1) {
			throw new InsertException(
				"创建订单失败！插入数据时出现未知错误[1]！");
		}
	}

	/**
	 * 插入订单商品数据
	 * @param orderItem 订单商品数据
	 */
	private void insertOrderItem(OrderItem orderItem) {
		Integer rows = orderMapper.insertOrderItem(orderItem);
		if (rows != 1) {
			throw new InsertException(
				"创建订单失败！插入数据时出现未知错误[2]！");
		}
	}

}





