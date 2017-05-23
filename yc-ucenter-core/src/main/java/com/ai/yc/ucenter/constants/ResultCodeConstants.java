package com.ai.yc.ucenter.constants;

public class ResultCodeConstants {

    public static final int ERROR_USER_NOT_EXIST = -101;
    public static final int ERROR_ACCOUNT_NOT_ACTIVITY = -102;
    public static final int ERROR_ACCOUNT_OR_PASSWORD_NOT_MATCH = -103;


    private ResultCodeConstants(){}
	
	public static final int SUCCESS_CODE = 1;
	
	public static final int NULL_CODE = 100001;

    public static final int ERROR_CODE = 0;
    /**
     * 用户不存在
     */
    public static final int USER_NON_EXIST = -1;
    /**
     * 用户名或密码错误
     */
    public static final int ACCOUNT_OR_PASSWD_ERROR = -2;
}
