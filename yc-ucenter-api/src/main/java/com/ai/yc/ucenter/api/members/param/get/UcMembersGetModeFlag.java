package com.ai.yc.ucenter.api.members.param.get;

import java.io.Serializable;

/**
 * 用户信息获取方式枚举类<br>
 * Date: 2016年11月1日 <br>
 * Copyright (c) 2016 asiainfo.com <br>
 *
 * @author mengbo
 */
public  class UcMembersGetModeFlag {
	 /**  
     * 用户id
     */
	public static String  USERID_FLAG="1"; 
	 /**  
     * 邮箱
     */
	public static String  EMAIL_FLAG="2"; 
	 /**  
     * 手机
     */
	public static String  MOBILE_FLAG="3"; 
	 /**  
     * 用户名
     */
	public static String  USERNAME_FLAG="4"; 
	 /**  
     * 用户名 或邮箱 或手机号
     */
	public static String  UME_FLAG="5"; 

}
