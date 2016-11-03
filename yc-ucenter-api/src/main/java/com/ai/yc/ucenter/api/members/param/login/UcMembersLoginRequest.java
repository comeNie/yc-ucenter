package com.ai.yc.ucenter.api.members.param.login;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.yc.ucenter.api.members.interfaces.IUcMembersSV;

public class UcMembersLoginRequest extends BaseInfo{
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 用户名称
     */
	@NotBlank(message="用户名称不能为空")
    private String username;

	
	/**
     * 密码
     */
	@NotBlank(message="密码不能为空")
    private String password;
    
    /**
     * 登录方式
      1：邮箱密码
		2：手机密码
		3：手机动态密码
		4：用户名密码

     */
	@NotBlank(message="登录方式不能为空")
    private String loginmode;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginmode() {
		return loginmode;
	}

	public void setLoginmode(String loginmode) {
		this.loginmode = loginmode;
	}
    
    
    
}
