package com.ai.yc.ucenter.api.members.param.editemail;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersEditEmailRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
    private Integer uid;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 验证码
     */
    private String operationcode;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}
    
    
    
}
