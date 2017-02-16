package com.ai.yc.ucpublic.constants;

public class RegResultCodeConstants {
	
	private RegResultCodeConstants(){}
	 /**
     * 表示用户注册成功
     */
	public static final int SUCCESS_CODE = 1;
	 /**
     * 表示用户注册失败
     */
	public static final int FAIL_CODE = 0;
	 /**
     * 用户名不合法
     */
	public static final int USERNAME_ERROR = -1;
	 /**
     * 包含不允许注册的词语
     */
    public static final int NOTALLOW_ERROR = -2;
    
    /**
     * 用户名已存在
     */
	public static final int USERNAME_EXISTS = -3;
    
    /**
     * Email 格式有误
     */
	public static final int EMAIL_ERROR= -4;
    /**
     * Email 不允许注册
     */
	public static final int EMAIL_NOTALLOW= -5;
    /**
     * 该Email 已经被注册
     */
	public static final int EMAIL_REGISTERED= -6;
    /**
     * 该手机已经被注册
     */
	public static final int MOBILE_REGISTERED= -7;
    /**
     * 验证码过期
     */
	public static final int VER_OVERDUE= -8;
	
	
}
