package com.ai.yc.ucenter.api.ucpubilc.param;

import org.hibernate.validator.constraints.NotBlank;

import com.ai.opt.base.vo.BaseInfo;

/** 
 * @author  作者 “WTF” E-mail: 1031248990@qq.com
 * @date 创建时间：2017年2月16日 下午3:33:09 
 * @version 
 * @since  
 */
public class UcEditPassRequest  extends BaseInfo {

	private static final long serialVersionUID = 1L;
	
	/**
     * 用户ID 
     */
	
    private Integer uid;
    
    /**
     * 旧密码或验证码
     */
	@NotBlank
    private String checke_code;
    
    /**
     * 新密码
     */
	@NotBlank
    private String newpw;
    /**
     * 账号身份验证方式
		1：旧密码
		2：验证码（密码操作验证码）

     */
	@NotBlank
    private String checke_mode;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
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
