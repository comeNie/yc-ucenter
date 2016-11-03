package com.ai.yc.ucenter.api.members.param.get;

import com.ai.opt.base.vo.BaseInfo;

public class UcMembersGetRequest extends BaseInfo implements Cloneable{
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 用户名称
     */
    private String username;
    /**
     * 获取方式
			1：用户id
			2：邮箱
			3：手机
			4：用户名
     */
    private String getmode;
    
    
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGetmode() {
		return getmode;
	}
	public void setGetmode(String getmode) {
		this.getmode = getmode;
	}
  
    
    
}
