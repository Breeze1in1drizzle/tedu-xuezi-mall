package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;

/**
 * 处理用户数据的业务层接口
 */
public interface IUserService {

	/**
	 * 用户注册
	 * @param user 尝试注册的用户数据
	 * @throws UsernameDuplicateException 用户名被占用时的异常
	 * @throws InsertException 插入数据失败时的异常
	 */
	void reg(User user) 
			throws UsernameDuplicateException, 
				InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 成功登录的用户信息
	 * @throws UserNotFoundException 用户数据不存在
	 * @throws PasswordNotMatchException 密码错误 
	 */
	User login(String username, String password) 
			throws UserNotFoundException, 
				PasswordNotMatchException;
}





