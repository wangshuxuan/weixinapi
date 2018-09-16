package com.kedacom.meeting.weixinapi.service;
/**
 * 用户service
 * @author: wangshuxuan 
 * @date: 2018年9月16日
 */
public interface UserService {
	
	/**
	 * 微信登录
	 * @param authCode
	 * @param username
	 * @param password
	 * @return
	 */
	public String wxLogin(String authCode, String username, String password);

}
