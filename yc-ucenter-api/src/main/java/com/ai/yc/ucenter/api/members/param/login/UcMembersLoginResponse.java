package com.ai.yc.ucenter.api.members.param.login;


import com.ai.yc.ucenter.api.members.param.base.UcBaseResponse;
import com.ai.yc.ucenter.api.members.param.base.UcResponseDate;

/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMembersLoginResponse extends UcBaseResponse {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public static class UcMembersLoginResponseDate extends UcResponseDate{  
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		/**
	     * 主键
	     */
	    private Integer uid;

	    /**
	     * 用户名称
	     */
	    private String username;

	    /**
	     * 密码
	     */
	    private String password;

	    /**
	     * 邮箱
	     */
	    private String email;

	     /**
	     * 移动电话（可用于登录）
	     */
	    private String mobilephone;

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

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobilephone() {
			return mobilephone;
		}

		public void setMobilephone(String mobilephone) {
			this.mobilephone = mobilephone;
		}
	}
	
	

}
