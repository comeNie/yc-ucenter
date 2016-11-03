package com.ai.yc.ucenter.api.members.param;

import java.io.Serializable;

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
     * 邮箱是否验证
     */
    private Integer emailcheck;

    /**
     * 漫游Id
     */
    private String myid;

    /**
     * 漫游Id
     */
    private Integer myidkey;

    /**
     * 注册IP
     */
    private String regip;
    
    /**
     * 注册时间
     */
    private Integer regdate;
    
    /**
     * 上次登陆的IP(程序转换成数值类型)
     */
    private String lastloginip;
    
    /**
     * 上次登录的时间
     */
    private Integer lastlogintime;
    
    /**
     * 密码干扰串，用来和密码进行配合验证，防止被暴力破解
     */
    private String salt;
    
    /**
     * 用户的安全提问
     */
    private String secques;
    
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
     * 注册方式
		1：邮箱密码
		2：手机密码
		3：手机动态密码
		4：用户名密码
     */
    private String loginway;
    /**
     * 激活状态
		0：未激活
		1：已激活
		（老系统账号此字段为空）
     */
    private String enablestatus;
    
    /**
     * 创建时间（根据创建时间判断，24小时未激活，删除未激活记录。）年月日时分秒
     */
    private String createtime;
    
    private String domainName;
    
    private Integer modifydate;
    
    private Integer logincount;
    
    private String loginsystem;

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

	public Integer getEmailcheck() {
		return emailcheck;
	}

	public void setEmailcheck(Integer emailcheck) {
		this.emailcheck = emailcheck;
	}

	public String getMyid() {
		return myid;
	}

	public void setMyid(String myid) {
		this.myid = myid;
	}

	public Integer getMyidkey() {
		return myidkey;
	}

	public void setMyidkey(Integer myidkey) {
		this.myidkey = myidkey;
	}

	public String getRegip() {
		return regip;
	}

	public void setRegip(String regip) {
		this.regip = regip;
	}

	public Integer getRegdate() {
		return regdate;
	}

	public void setRegdate(Integer regdate) {
		this.regdate = regdate;
	}

	public String getLastloginip() {
		return lastloginip;
	}

	public void setLastloginip(String lastloginip) {
		this.lastloginip = lastloginip;
	}

	public Integer getLastlogIntegerime() {
		return lastlogintime;
	}

	public void setLastlogIntegerime(Integer lastlogintime) {
		this.lastlogintime = lastlogintime;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getSecques() {
		return secques;
	}

	public void setSecques(String secques) {
		this.secques = secques;
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

	public String getLoginway() {
		return loginway;
	}

	public void setLoginway(String loginway) {
		this.loginway = loginway;
	}

	public String getEnablestatus() {
		return enablestatus;
	}

	public void setEnablestatus(String enablestatus) {
		this.enablestatus = enablestatus;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Integer getModifydate() {
		return modifydate;
	}

	public void setModifydate(Integer modifydate) {
		this.modifydate = modifydate;
	}

	public Integer getLogincount() {
		return logincount;
	}

	public void setLogincount(Integer logincount) {
		this.logincount = logincount;
	}

	public String getLoginsystem() {
		return loginsystem;
	}

	public void setLoginsystem(String loginsystem) {
		this.loginsystem = loginsystem;
	}
    
    

}
