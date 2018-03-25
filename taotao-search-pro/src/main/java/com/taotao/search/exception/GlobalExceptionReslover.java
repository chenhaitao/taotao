package com.taotao.search.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class GlobalExceptionReslover implements HandlerExceptionResolver {

	private static final Logger logger =  LoggerFactory.getLogger(GlobalExceptionReslover.class);
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		logger.info("进入全局异常处理");
		logger.debug("测试类型:"+handler.getClass());
		
		exception.printStackTrace();
		
		logger.error("异常错误", exception);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("error/exception");
		modelAndView.addObject("message", "发生错误，请稍后再试！");
		
		return modelAndView;
	}

}
