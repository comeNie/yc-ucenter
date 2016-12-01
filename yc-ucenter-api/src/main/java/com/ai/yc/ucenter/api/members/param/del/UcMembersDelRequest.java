package com.ai.yc.ucenter.api.members.param.del;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersDelRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
	/**
     * 用户ID （必填）
     */
	
    private Integer uid;
    



	public Integer getUid() {
		return uid;
	}


	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
    
}
