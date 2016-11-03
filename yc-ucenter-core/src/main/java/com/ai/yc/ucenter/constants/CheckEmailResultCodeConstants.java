package com.ai.yc.ucenter.constants;

public class CheckEmailResultCodeConstants {
	
	private CheckEmailResultCodeConstants(){}
	 /**
     * 表示成功 
     */
	public static final String SUCCESS_CODE = "1";
	 /**
     * Email 格式有误
     */
	public static final String FORMAT_ERROR = "-4";
	 /**
     * Email 不允许注册
     */
    public static final String NOTALLOW_ERROR = "-5";
	 /**
     * 该 Email 已经被注册
     */
    public static final String EXIST_ERROR = "-6";
   
	
}
