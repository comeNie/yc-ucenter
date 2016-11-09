package com.ai.yc.ucenter.constants;

public class EditMobileResultCodeConstants {
	
	private EditMobileResultCodeConstants(){}
	 /**
     * 表示更新成功
     */
	public static final int SUCCESS_CODE = 1;
	 /**
     * 该电话已经被注册，修改/绑定失败
     */
	public static final int EXISTS_ERROR = -1;
	 /**
     * 验证码过期，修改/绑定失败
     */
    public static final int OVERDUE_ERROR = 0;
    /**
     * 失败
     */
	public static final int FAIL_CODE = -1;
    
   
	
}
