package com.kedacom.meeting.weixinapi.service;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kedacom.meeting.weixinapi.config.WxAccountConfig;
import com.kedacom.meeting.weixinapi.constant.RedisConstant;
import com.kedacom.meeting.weixinapi.util.HttpRequestUtil;

/**
 * 用户service实现
 * @author: wangshuxuan 
 * @date: 2018年9月16日
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private WxAccountConfig wxAccountConfig;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	private String ssotoken;//TODO
	
	/**
	 * 微信登陆
	 * @param authCode
	 * @param username
	 * @param password
	 * @return
	 * @see com.kedacom.meeting.weixinapi.service.UserService#wxLogin(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String wxLogin(String authCode, String username, String password) {
		try {
			if (StringUtils.isEmpty(authCode)) {
				return null;
			}
			
			//TODO 调用kis或平台api校验
			
			StringBuffer sb = new StringBuffer();
			sb.append("appid=").append(wxAccountConfig.getAppId());
			sb.append("&secret=").append(wxAccountConfig.getSecret());
			sb.append("&js_code=").append(authCode);
			sb.append("&grant_type=").append(wxAccountConfig.getGrantType());
			String responseStr = HttpRequestUtil.sendGet(wxAccountConfig.getUrl(), sb.toString());
			
			ObjectMapper mapper = new ObjectMapper();
			Map resMap = mapper.readValue(responseStr, Map.class);
		    Object object = resMap.get("openid");
			if(StringUtils.isEmpty(object)||StringUtils.isEmpty(resMap.get("session_key"))){
                return null;
            }
		    String openid = (String) resMap.get("openid");
		    String session_key = (String) resMap.get("session_key");
		    
		    redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, ssotoken), 
		    		openid + session_key, RedisConstant.EXPIRE, TimeUnit.SECONDS);
		    return ssotoken;
		    
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
