package com.ai.yc.ucenter.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ai.opt.sdk.util.DateUtil;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;



public class UcmembersValidators {
	
	private UcmembersValidators(){}


	
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

	/**
	 * 校验用户名是否存在
	 * @param username
	 * @return
	 */
	public static boolean validateUsername(String username){
		UcMembersCriteria example = new UcMembersCriteria();
		

		Criteria orUsername = example.or();
		orUsername.andUsernameEqualTo(username);
		orUsername.andEnablestatusEqualTo("1");
		Criteria orMobile = example.or();
		orMobile.andMobilephoneEqualTo(username);
		orMobile.andEnablestatusEqualTo("1");
		Criteria orEmail = example.or();
		orEmail.andEmailEqualTo(username);
		orEmail.andEnablestatusEqualTo("1");
		
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		if(list.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 校验手机号码是否存在
	 * 
	 * @param args
	 */
	public static boolean validateMobilephone(String mobilephone){
		UcMembersCriteria example = new UcMembersCriteria();
		
//		Criteria criteria = example.createCriteria();
//		criteria.andMobilephoneEqualTo(mobilephone);
		//验证激活状态下
	
		
		Criteria orUsername = example.or();
		orUsername.andUsernameEqualTo(mobilephone);
		orUsername.andEnablestatusEqualTo("1");
		Criteria orMobile = example.or();
		orMobile.andMobilephoneEqualTo(mobilephone);
		orMobile.andEnablestatusEqualTo("1");
	
		
		
		
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		if(list.size()>0){
			return false;
		}
		return true;
	}
	
	/**
	 * 校验邮箱是否存在
	 * 
	 * @param args
	 */
	public static boolean validateEmail(String email){
		UcMembersCriteria example = new UcMembersCriteria();
	
		
		Criteria orUsername = example.or();
		orUsername.andUsernameEqualTo(email);
		orUsername.andEnablestatusEqualTo("1");

		Criteria orEmail = example.or();
		orEmail.andEmailEqualTo(email);
		orEmail.andEnablestatusEqualTo("1");
		

		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		if(list.size()>0){
			return false;
		}
		return true;
		
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
