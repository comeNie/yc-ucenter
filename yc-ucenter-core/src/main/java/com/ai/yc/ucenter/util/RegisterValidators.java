package com.ai.yc.ucenter.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;


public class RegisterValidators {
	
	private RegisterValidators(){}

	
	/**
	 * 验证用户名电话邮箱必输一项
	 * 
	 * @param UcMembers
	 * @return boolean
	 */
	public static boolean validateUserAccount(UcMembersRegisterRequest request){
		String username = request.getUsername();
		String email = request.getEmail();
		String mobilephone = request.getMobilephone();
		if(StringUtils.isNotBlank(username) || StringUtils.isNotBlank(email) || StringUtils.isNotBlank(mobilephone)){
			return true;
		}
			return false;
		
	}


	public static void main(String[] args) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timeStart;
		try {
			timeStart = sdf.parse(DateUtil.getCurrentTime()).getTime();

		} catch (ParseException e) {
			
			e.printStackTrace();
		}
	}
}
