package cn.tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/reg")
	public ResponseResult<Void> reg(User user) {
		ResponseResult<Void> rr
			= new ResponseResult<Void>();
		
		try {
			userService.reg(user);
			rr.setState(1);
		} catch (ServiceException e) {
			rr.setState(2);
			rr.setMessage(e.getMessage());
		}
		
		return rr;
	}
	
}







