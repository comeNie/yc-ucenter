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
