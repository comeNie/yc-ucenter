package com.ai.yc.ucenter.api.members.param.editpass;


import com.ai.opt.base.vo.BaseResponse;

/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMemEditPassbersResponse extends BaseResponse {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    private String uid;

    /**
     * 旧密码或验证码
     */
    private String checke_code;

    /**
     * 新密码
     */
    private String newpw;

    /**
     * 账号身份验证方式
		1：旧密码
		2：验证码（密码操作验证码）

     */
    private String checke_mode;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getChecke_code() {
		return checke_code;
	}

	public void setChecke_code(String checke_code) {
		this.checke_code = checke_code;
	}

	public String getNewpw() {
		return newpw;
	}

	public void setNewpw(String newpw) {
		this.newpw = newpw;
	}

	public String getChecke_mode() {
		return checke_mode;
	}

	public void setChecke_mode(String checke_mode) {
		this.checke_mode = checke_mode;
	}

    
    

}
