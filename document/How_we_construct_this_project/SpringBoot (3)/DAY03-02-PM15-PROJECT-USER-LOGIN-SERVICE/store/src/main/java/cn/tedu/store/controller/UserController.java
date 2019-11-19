package cn.tedu.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	
	@PostMapping("/reg")
	public ResponseResult<Void> reg(User user) {
		userService.reg(user);
		return new ResponseResult<Void>(SUCCESS);
	}
	
}







