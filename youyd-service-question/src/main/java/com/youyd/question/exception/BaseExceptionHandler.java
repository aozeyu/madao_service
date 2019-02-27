package com.youyd.question.exception;

import com.youyd.pojo.Result;
import com.youyd.utils.LogBack;
import com.youyd.utils.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description: 统一异常处理类
 * @author: LGG
 * @create: 2018-09-26 16:06
 **/
@ControllerAdvice
public class BaseExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result error(Exception ex) {
		LogBack.error(ex.getMessage(),ex);
		ex.printStackTrace();
		return new Result(false, StatusCode.ERROR.getCode(), ex.getMessage());
	}
}