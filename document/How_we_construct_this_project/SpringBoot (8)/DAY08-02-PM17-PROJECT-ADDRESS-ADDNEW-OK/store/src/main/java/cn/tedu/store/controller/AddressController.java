package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/addresses")
public class AddressController extends BaseController {

	@Autowired
	private IAddressService addressService;
	
	@RequestMapping("/addnew")
	public ResponseResult<Void> addnew(
			Address address, HttpSession session) {
		// 从session中获取uid
		Integer uid = getUidFromSession(session);
		// 将uid封装到address中
		address.setUid(uid);
		// 从session中获取username
		String username = session.getAttribute("username").toString();
		// 调用业务层对象执行：addressService.addnew(address, username);
		addressService.addnew(address, username);
		// 返回成功
		return new ResponseResult<>(SUCCESS);
	}
	
}












