package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.ex.InsertException;
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
	
}





