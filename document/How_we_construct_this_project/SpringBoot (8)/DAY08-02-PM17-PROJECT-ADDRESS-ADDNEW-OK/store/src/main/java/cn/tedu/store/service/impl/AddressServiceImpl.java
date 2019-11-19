package cn.tedu.store.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.tedu.store.entity.Address;
import cn.tedu.store.entity.District;
import cn.tedu.store.mapper.AddressMapper;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.service.IDistrictService;
import cn.tedu.store.service.ex.InsertException;

/**
 * 处理收货地址数据的业务层实现类
 */
@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private IDistrictService districtService;

	@Override
	public void addnew(Address address, String username) throws InsertException {
		// 查询用户的收货地址的数量：countByUid(Integer uid)，参数值来自address.getUid();
		Integer count = countByUid(address.getUid());
		// 判断数量是否为0
		// 是：当前将增加第1条收货地址，则：address.setIsDefault(1)
		// 否：当前增加的不是第1条收货地址，则：address.setIsDefault(0)
		address.setIsDefault(count == 0 ? 1 : 0);

		// 处理district
		String district = getDistrict(
				address.getProvince(), address.getCity(), address.getArea());
		address.setDistrict(district);

		// 4项日志：时间是直接创建对象得到，用户名使用参数username
		Date now = new Date();
		address.setCreatedUser(username);
		address.setCreatedTime(now);
		address.setModifiedUser(username);
		address.setModifiedTime(now);

		// 执行增加：insert(Address address);
		insert(address);
	}
	
	/**
	 * 增加收货地址数据
	 * @param address 收货地址数据
	 */
	private void insert(Address address) {
		Integer rows = addressMapper.insert(address);
		if (rows != 1) {
			throw new InsertException(
				"增加收货地址数据时出现未知错误！");
		}
	}

	/**
	 * 统计指定用户的收货地址数据的数量
	 * @param uid 用户的id
	 * @return 用户的收货地址数据的数量
	 */
	private Integer countByUid(Integer uid) {
		return addressMapper.countByUid(uid);
	}
	
	/**
	 * 根据省、市、区的代号，获取名称
	 * @param province 省的代号
	 * @param city 市的代号
	 * @param area 区的代号
	 * @return 省、市、区的代号对应的名称
	 */
	private String getDistrict(
			String province, String city, String area) {
		District p = districtService.getByCode(province);
		District c = districtService.getByCode(city);
		District a = districtService.getByCode(area);
		String provinceName = p == null ? "NULL" : p.getName();
		String cityName = c == null ? "NULL" : c.getName();
		String areaName = a == null ? "NULL" : a.getName();
		StringBuffer result = new StringBuffer();
		result.append(provinceName);
		result.append(",");
		result.append(cityName);
		result.append(",");
		result.append(areaName);
		return result.toString();
	}

}



