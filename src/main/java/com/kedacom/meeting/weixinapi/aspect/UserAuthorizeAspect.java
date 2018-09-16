package com.kedacom.meeting.weixinapi.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * aop认证用户
 * @author: wangshuxuan 
 * @date: 2018年9月16日
 */
@Aspect
@Component
public class UserAuthorizeAspect {
	
	@Autowired
    private StringRedisTemplate redisTemplate;
	
	@Pointcut("execution(public * com.kedacom.meeting.weixinapi.controller.*(..))")
	public void verify() {}
	
	@Before("verify()")
    public void doVerify() {
		 ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	     HttpServletRequest request = attributes.getRequest();
	     //TODO redis校验用户身份
	}
}
