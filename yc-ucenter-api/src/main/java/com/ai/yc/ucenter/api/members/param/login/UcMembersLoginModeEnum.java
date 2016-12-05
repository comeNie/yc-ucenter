package com.ai.yc.ucenter.api.members.param.login;

import java.io.Serializable;

/**
 * 登录方式枚举类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public class UcMembersLoginModeEnum {
	
	private UcMembersLoginModeEnum(){}
	 /**
    * 邮箱密码
    */
	public static final String EMAILPASS_MODE = "1";
	 /**
    * 手机密码
    */
	public static final String PHONEPASS_MODE = "2";
	 /**
    * 手机动态密码
    */
   public static final String PHONEDYNPASS_MODE= "3";
	 /**
    * 用户名密码
    */
   public static final String USERPASS_MODE = "4"; 
   /**
    * 用户名密码
    */
   public static final String ALL_MODE = "5"; 

}
