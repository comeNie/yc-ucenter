package com.ai.yc.ucenter.api.members.param.checke;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersCheckEmailRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 主键
     */
    private Integer uid;
    
    /**
     * 邮箱
     */
    private String email;
    
   

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


    
    
}
