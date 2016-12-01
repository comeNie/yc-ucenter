package com.ai.yc.ucenter.api.members.param.editusername;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersEditUserNameRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 用户ID （必填）
     */
	
    private Integer uid;
    

    /**
     * 用户名（必填）
     */
	@NotBlank
    private String username;


	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
    
}
