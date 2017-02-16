package com.ai.yc.ucpublic.util;


import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;

public class LoginValidators {
	
	private LoginValidators(){}
	private static String ACTIVATE_STATUS = "1";
	private static String NOTACTIVATE_STATUS = "0";
	
	/**
	 * 验证账号激活状态
	 * 激活状态
		0：未激活
		1：已激活
		（老系统账号此字段为空
	 * @param ucMembers
	 * @return
	 */
	public static boolean validateEnablestatus(UcMembers ucMembers){
		if(NOTACTIVATE_STATUS.equals(ucMembers.getEnablestatus())){
			return false;
		}else{
			return true;
		}
		
	}

}
