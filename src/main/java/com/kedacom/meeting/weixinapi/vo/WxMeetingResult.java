package com.kedacom.meeting.weixinapi.vo;
/**
 * 响应 值对象.
 * @author: wangshuxuan 
 * @date: 2018年9月16日
 */

public class WxMeetingResult {
	
	private boolean success;
	private String message;
	private Object body;
	
	/** 响应处理是否成功 */
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/** 响应处理的消息 */
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	/** 响应处理的返回内容 */
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}

	public WxMeetingResult(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public WxMeetingResult(boolean success, String message, Object body) {
		this.success = success;
		this.message = message;
		this.body = body;
	}

}
