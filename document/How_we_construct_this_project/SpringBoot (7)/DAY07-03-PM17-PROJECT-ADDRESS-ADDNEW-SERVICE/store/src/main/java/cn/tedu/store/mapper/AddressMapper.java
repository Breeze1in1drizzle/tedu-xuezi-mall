package cn.tedu.store.mapper;

import cn.tedu.store.entity.Address;

/**
 * 处理收货地址的持久层接口
 */
public interface AddressMapper {

	/**
	 * 增加收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer insert(Address address);

	/**
	 * 统计指定用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 用户的收货地址数据的数量
	 */
	Integer countByUid(Integer uid);
	
}






