package com.ai.yc.ucenter.api.members.param.get;


import com.ai.opt.base.vo.BaseResponse;

/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMembersGetResponse extends BaseResponse {
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
