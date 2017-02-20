package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:29:24 
 * @version 
 * @since  
 */
public class UcLoginMemberRequest extends BaseInfo {
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
	 	5. 都行
     */
	@NotBlank(message="登录方式不能为空")
	private String loginmode;
	/**
	 * 系统来源(默认为0)
	 */
	private int systemsource;
	/**
	 * 登录IP
	 */
	private String loginip;
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
	public int getSystemsource() {
		return systemsource;
	}
	public void setSystemsource(int systemsource) {
		this.systemsource = systemsource;
	}
	public String getLoginip() {
		return loginip;
	}
	public void setLoginip(String loginip) {
		this.loginip = loginip;
	}
	
	
}
