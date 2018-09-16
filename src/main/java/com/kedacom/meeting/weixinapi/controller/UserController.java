package com.kedacom.meeting.weixinapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.meeting.weixinapi.service.UserService;
import com.kedacom.meeting.weixinapi.vo.WxMeetingResult;

/**
 * 小程序用户controller
 * @author: wangshuxuan 
 * @date: 2018年9月16日
 */
@RestController
@RequestMapping()  //TODO
public class UserController {
	
	 @Autowired
	 private UserService userService;

	
	/**
	 * 登录认证
	 */
	@PostMapping(value = "/login") //TODO
	public WxMeetingResult wxLogin(@RequestParam("authCode") String authCode, 
									String username, String password) {
		String session_id = userService.wxLogin(authCode, username, password);
		return null;
	}
}
