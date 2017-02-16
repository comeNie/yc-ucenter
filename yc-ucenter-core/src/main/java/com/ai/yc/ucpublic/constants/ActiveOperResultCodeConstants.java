package com.ai.yc.ucpublic.constants;

public class ActiveOperResultCodeConstants {

	private ActiveOperResultCodeConstants(){}
	
	 /**
     * 成功
     */
	public static final String SUCCESS_CODE = "1";
	 /**
     * 该账号是已激活账号
     */
	public static final String UID_ACTIVED= "2";
	 /**
     *	激活码不对
     */
	public static final String VAL_ERROR= "-1";
	 /**
     * 验证超时，请重新发送激活码
     */
	public static final String VAL_TIMEOUT = "-13";
	 /**
     * 验证码不对，请重新输入
     */
	public static final String ACTIV_ERROR= "-14";
	public static String getSuccessCode() {
		return SUCCESS_CODE;
	}
	public static String getUidActived() {
		return UID_ACTIVED;
	}
	public static String getValError() {
		return VAL_ERROR;
	}
	public static String getValTimeout() {
		return VAL_TIMEOUT;
	}
	public static String getActivError() {
		return ACTIV_ERROR;
	}
	
	
}
