package com.ai.yc.ucenter.api.members.param.editmobile;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersEditMobileRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */

    private Integer uid;
    
    /**
     * 移动电话（可用于登录）
     */
	@NotBlank
    private String mobilephone;
    
    /**
     * 验证码
     */
	@NotBlank
    private String operationcode;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}
    
    
    
}
