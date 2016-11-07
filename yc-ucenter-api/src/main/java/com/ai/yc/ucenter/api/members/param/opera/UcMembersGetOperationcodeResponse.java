package com.ai.yc.ucenter.api.members.param.opera;


import com.ai.yc.ucenter.api.members.param.base.UcBaseResponse;
  
/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMembersGetOperationcodeResponse extends UcBaseResponse {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 操作码
     */
	private Object operationcode;

	public Object getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(Object operationcode) {
		this.operationcode = operationcode;
	}
	
	
}
