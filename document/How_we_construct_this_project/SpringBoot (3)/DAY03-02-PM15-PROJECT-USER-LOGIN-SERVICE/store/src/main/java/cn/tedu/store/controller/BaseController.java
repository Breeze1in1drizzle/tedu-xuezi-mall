package cn.tedu.store.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
import cn.tedu.store.util.ResponseResult;

/**
 * 控制器类的基类
 */
abstract class BaseController {
	
	/**
	 * 响应结果的状态：成功
	 */
	public static final Integer SUCCESS = 200;

	/**
	 * 统一处理异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ServiceException.class)
	public ResponseResult<Void> handleException(Throwable e) {
		ResponseResult<Void> rr
			= new ResponseResult<Void>();
		rr.setMessage(e.getMessage());
		
		if (e instanceof UsernameDuplicateException) {
			// 400-用户名冲突
			rr.setState(400);
		} else if (e instanceof InsertException) {
			// 500-插入数据异常
			rr.setState(500);
		}
		
		return rr;
	}
	
}
