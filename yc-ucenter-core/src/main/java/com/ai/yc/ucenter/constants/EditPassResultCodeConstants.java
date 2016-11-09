package com.ai.yc.ucenter.constants;

public class EditPassResultCodeConstants {
	
	private EditPassResultCodeConstants(){}
	 /**
     * 表示更新成功
     */
	public static final int SUCCESS_CODE = 1;
	 /**
     * 表示更新失败
     */
	public static final int FAIL_CODE = 0;
	 /**
     * 旧密码输入有误，修改失败
     */
	public static final int OLDPASS_ERROR = -1;
	 /**
     * 没有生效记录，修改失败
     */
	public static final int NONERECORD_ERROR = -7;
	 /**
     * 验证码过期，修改/绑定失败
     */
    public static final int OVERDUE_ERROR = 0;
    
   
	
}
