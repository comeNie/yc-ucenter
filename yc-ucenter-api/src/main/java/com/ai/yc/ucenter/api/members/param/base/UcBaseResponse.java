package com.ai.yc.ucenter.api.members.param.base;

import java.io.Serializable;



public class UcBaseResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 成功标识
	 * 0：失败 1：成功
	 */
	private ResponseMessage message;

	private ResponseCode code;
	
	/**
	 * 消息体
	 *uid：用户ID
	 *operationcode：邮箱激活码
	 */
	private UcResponseDate date;
	

	public UcResponseDate getDate() {
		return date;
	}


	public void setDate(UcResponseDate date) {
		this.date = date;
	}
	
	public UcBaseResponse(){}
	
	
	

	public ResponseCode getCode() {
		return code;
	}


	public void setCode(ResponseCode code) {
		this.code = code;
	}


	public ResponseMessage getMessage() {
		return message;
	}


	public void setMessage(ResponseMessage message) {
		this.message = message;
	}


	
	
	
	
}
