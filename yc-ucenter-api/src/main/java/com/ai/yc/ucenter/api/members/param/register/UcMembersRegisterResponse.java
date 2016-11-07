package com.ai.yc.ucenter.api.members.param.register;


import com.ai.opt.base.vo.BaseResponse;
import com.ai.yc.ucenter.api.members.param.base.UcBaseResponse;

/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMembersRegisterResponse extends UcBaseResponse {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * 消息体
	 *uid：用户ID
	 *operationcode：邮箱激活码
	 */
	private UcMembersRegisterResponseDate date;
	

	public UcMembersRegisterResponseDate getDate() {
		return date;
	}


	public void setDate(UcMembersRegisterResponseDate date) {
		this.date = date;
	}


	public static class UcMembersRegisterResponseDate{
		/**
	     * 主键
	     */
	    private String uid;

	    /**
	     * 邮箱激活码
	     */
	    private String operationcode;

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}
		
		public String getOperationcode() {
			return operationcode;
		}

		public void setOperationcode(String operationcode) {
			this.operationcode = operationcode;
		}
	}
	



  
    

}
