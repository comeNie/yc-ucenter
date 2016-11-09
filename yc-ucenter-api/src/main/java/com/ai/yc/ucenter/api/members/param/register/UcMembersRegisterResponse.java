package com.ai.yc.ucenter.api.members.param.register;


import com.ai.opt.base.vo.BaseResponse;
import com.ai.yc.ucenter.api.members.param.base.UcBaseResponse;
import com.ai.yc.ucenter.api.members.param.base.UcResponseDate;

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

	
	


	public static class UcMembersRegisterResponseDate extends UcResponseDate{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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
