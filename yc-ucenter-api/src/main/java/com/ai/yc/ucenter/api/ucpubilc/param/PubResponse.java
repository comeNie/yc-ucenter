package com.ai.yc.ucenter.api.ucpubilc.param;

import java.io.Serializable;

import com.ai.yc.ucenter.api.members.param.base.ResponseCode;
import com.ai.yc.ucenter.api.members.param.base.ResponseMessage;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月15日 下午2:06:07 
 * @version 
 * @since  
 */
public class PubResponse<T> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ResponseMessage message;

	private ResponseCode code;
	
	private T data;

	public ResponseMessage getMessage() {
		return message;
	}

	public void setMessage(ResponseMessage message) {
		this.message = message;
	}

	public ResponseCode getCode() {
		return code;
	}

	public void setCode(ResponseCode code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	

}
