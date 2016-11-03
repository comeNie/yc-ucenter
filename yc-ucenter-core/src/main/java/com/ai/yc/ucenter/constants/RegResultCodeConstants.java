package com.ai.yc.ucenter.constants;

public class RegResultCodeConstants {
	
	private RegResultCodeConstants(){}
	 /**
     * 表示用户注册成功
     */
	public static final String SUCCESS_CODE = "1";
	 /**
     * 表示用户注册失败
     */
	public static final String FAIL_CODE = "0";
	 /**
     * 用户名不合法
     */
	public static final String USERNAME_ERROR = "-1";
	 /**
     * 包含不允许注册的词语
     */
    public static final String NOTALLOW_ERROR = "-2";
    
    /**
     * 用户名已存在
     */
	public static final String USERNAME_EXISTS = "-3";
    
    /**
     * Email 格式有误
     */
	public static final String EMAIL_ERROR= "-4";
    /**
     * Email 不允许注册
     */
	public static final String EMAIL_NOTALLOW= "-5";
    /**
     * 该Email 已经被注册
     */
	public static final String EMAIL_REGISTERED= "-6";
    /**
     * 该手机已经被注册
     */
	public static final String MOBILE_REGISTERED= "-7";
    /**
     * 验证码过期
     */
	public static final String VER_OVERDUE= "-8";
	
	
}
