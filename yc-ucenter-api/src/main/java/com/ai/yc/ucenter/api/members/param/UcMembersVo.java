package com.ai.yc.ucenter.api.members.param;

import java.io.Serializable;
import java.util.Map;

import com.ai.opt.base.vo.BaseInfo;

/**
 * Ucenter-用户信息查询结果类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */ 
public class UcMembersVo extends BaseInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	public UcMembersVo(Map<?, ?> map){
			UcBeanUtils.transMap2Bean(map, this);	

	}

	/**
     * 主键
     */
    private String uid;

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
  
 
    private String systemsource;
    
    /**
     * 用户来源系统 
		0：内部系统 gtcom
		1：金山 jinshan
		2：百度用户baidu
		3：微信 weixin
		4：腾讯用户qq
		5：新浪用户sina
     */
    private String usersource;
    
    /**
     * 第三方系统用户ID
     */
    private String thirduid;
    
    /**
     * 许可登录方式，默认全部
		0：全部
		1：邮箱密码
		2：手机密码
		3：手机动态密码
		4：用户名密码

     */
    private String loginmode;
  

    
    /**
     * 创建时间（根据创建时间判断，24小时未激活，删除未激活记录。）年月日时分秒
     */
    private String createtime;
    

    /**
     * 操作码或激活码
     */
    private String operationcode;

    
    
	public String getOperationcode() {
		return operationcode;
	}

	public void setOperationcode(String operationcode) {
		this.operationcode = operationcode;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
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

	public String getSystemsource() {
		return systemsource;
	}

	public void setSystemsource(String systemsource) {
		this.systemsource = systemsource;
	}

	public String getUsersource() {
		return usersource;
	}

	public void setUsersource(String usersource) {
		this.usersource = usersource;
	}

	public String getThirduid() {
		return thirduid;
	}

	public void setThirduid(String thirduid) {
		this.thirduid = thirduid;
	}

	public String getLoginmode() {
		return loginmode;
	}

	public void setLoginmode(String loginmode) {
		this.loginmode = loginmode;
	}





	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "UcMembersVo [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", mobilephone=" + mobilephone + ", systemsource=" + systemsource + ", usersource=" + usersource
				+ ", thirduid=" + thirduid + ", loginmode=" + loginmode + ", createtime=" + createtime
				+ ", operationcode=" + operationcode + "]";
	}


}
