package com.ai.yc.ucpublic.util;

public class OperationValidateUtils {
	
	//private static final Logger LOG = LoggerFactory.getLogger(OperationValidateUtils.class);
	

	/**
	 * 手机激活码或动态密码一致性验证
	 * 
	 * @param operationtime
	 * @return
	 */
	
	/**
	 * 手机激活码或动态密码30分钟有效
	 * @param operationtime
	 * @return
	 */
	public static boolean mobileExpirytime(long operationtime){
		long systime = UCDateUtils.getSystime();
		long c = systime - operationtime;
		long time=30*60*1000;//30分钟

		if(c<=time){
			return true;
		}
		return false;
	}
	/**
	 * 邮箱验证码60分钟有效
	 * @param operationtime
	 * @return
	 */
	public static boolean emailExpirytime(long operationtime){
		long systime = UCDateUtils.getSystime();
		long c = systime - operationtime;
		long time=60*60*1000;//60分钟

		if(c<=time){
			return true;
		}
		return false;
	}
	/**
	 * 邮箱验证码60分钟有效
	 * 
	 * @param operationtime
	 * @return
	
	/**
	 * 手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取。
	 * 
	 * @param operationtime
	 * @return
	 */
	public static boolean gainAgain(String operationtime){
		
		
		return false;
	}
	
	
	
	
	
	public static void main(String[] args) {
		

	}
}
