package com.ai.yc.ucpublic.constants;

public class CheckEmailResultCodeConstants {
	
	private CheckEmailResultCodeConstants(){}
	 /**
     * 表示成功 
     */
	public static final int SUCCESS_CODE = 1;
	 /**
     * Email 格式有误
     */
	public static final int FORMAT_ERROR = -4;
	 /**
     * Email 不允许注册
     */
    public static final int NOTALLOW_ERROR = -5;
	 /**
     * 该 Email 已经被注册
     */
    public static final int EXIST_ERROR = -6;
    /**
     * 失败
     */
	public static final int FAIL_CODE = 0;
   
	
}
