package com.ai.yc.ucenter.service.atom.members.impl;


import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetModeFlag;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.constants.Constants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.util.PasswordMD5Util;
import com.ai.yc.ucenter.util.UCDateUtils;



@Component
public class UcMembersServiceAtomImpl implements IUcMembersAtomService {

	@Override
	public List<UcMembers> loginMember(String username, String passMd5, String loginmode) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		//邮箱密码登录
		if(UcMembersLoginModeEnum.EMAILPASS_MODE.equals(loginmode)){
			criteria.andEmailEqualTo(username);
			criteria.andPasswordEqualTo(passMd5);
			
		}
		//手机动态密码
		else if(UcMembersLoginModeEnum.PHONEDYNPASS_MODE.equals(loginmode)){
			
		}
		//手机密码
		else if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(loginmode)){
			criteria.andMobilephoneEqualTo(username);
			criteria.andPasswordEqualTo(passMd5);
		}
		//用户名密码
		else if(UcMembersLoginModeEnum.USERPASS_MODE.equals(loginmode)){
				criteria.andUsernameEqualTo(username);
				criteria.andPasswordEqualTo(passMd5);
		}		
		criteria.andEnablestatusNotEqualTo("0");
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		
		return list;
	}

	@Override
	public String insertMember(UcMembersRegisterRequest request) throws Exception {
		UcMembers ucMembers =  new UcMembers();
		BeanUtils.copyProperties(ucMembers, request);
		String salt = PasswordMD5Util.creatSalt();
		ucMembers.setSalt(salt);
		ucMembers.setPassword(PasswordMD5Util.getPassSaltMd5(request.getPassword(),salt));
		ucMembers.setUsername(getUsername(request));
		//未激活
		ucMembers.setEnablestatus("0");
		
		//必填
		ucMembers.setEmailcheck(0); 
		Integer regdate =(int)UCDateUtils.getSystime() ;
		ucMembers.setRegdate(regdate);
		ucMembers.setLastloginip("0");
		
		ucMembers.setLogincount(1);
		ucMembers.setModifydate(0);

		int insertCount = MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			
			Integer newId = MapperFactory.getUcMembersMapper().selectPrimaryKey(ucMembers).getUid();
		
			return String.valueOf(newId);
		}else{
			return null;
		}

	}

	/**
	 * 用邮箱或是手机注册的账号，账号的用户名则用邮箱或手机
	 * @param request
	 * @return
	 */
	private String getUsername(UcMembersRegisterRequest request){
		StringBuffer username = new StringBuffer();
		String loginway = request.getLoginway();
		if(Constants.LoginWayConstant.EMAIL_PASS.equals(loginway)){
			username.append(request.getEmail());
		}else if(Constants.LoginWayConstant.MOBILE_DYNA.equals(loginway) || Constants.LoginWayConstant.MOBILE_PASS.equals(loginway)){
			username.append(request.getMobilephone());
		}else if(Constants.LoginWayConstant.USER_PASS.equals(loginway)){
			username.append(request.getUsername());
		}
		return username+"";
	}
	
	@Override
	public List<UcMembers> getMember(UcMembersGetRequest request) {
	
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		String reqUsername = request.getUsername();
		//用户id获取
		if(UcMembersGetModeFlag.USERID_FLAG.equals(request.getGetmode())){
			criteria.andUidEqualTo(Integer.parseInt(reqUsername));
		}//邮箱获取
		else if(UcMembersGetModeFlag.EMAIL_FLAG.equals(request.getGetmode())){
			criteria.andEmailEqualTo(reqUsername);
		}//手机获取
		else if(UcMembersGetModeFlag.MOBILE_FLAG.equals(request.getGetmode())){
			criteria.andMobilephoneEqualTo(reqUsername);
		}//用户名获取
		else if(UcMembersGetModeFlag.USERNAME_FLAG.equals(request.getGetmode())){
			criteria.andUsernameEqualTo(reqUsername);
		}
		criteria.andEnablestatusEqualTo("1");
		List<UcMembers> list = MapperFactory.getUcMembersMapper().selectByExample(example);
	
		return list;
	}

	
	
	@Override
	public int updateMobilephone(UcMembersEditMobileRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(request.getUid());
		UcMembers ucMembers = new UcMembers();
		ucMembers.setMobilephone(request.getMobilephone());
		
		return MapperFactory.getUcMembersMapper().updateByExample(ucMembers, example);
	}

	@Override
	public int updateEmail(UcMembersEditEmailRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(request.getUid());
		UcMembers ucMembers = new UcMembers();
		ucMembers.setEmail(request.getEmail());
		return MapperFactory.getUcMembersMapper().updateByExample(ucMembers, example);

	}

	@Override
	public int updatePassword(UcMembers ucMembers) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(ucMembers.getUid());
		return MapperFactory.getUcMembersMapper().updateByExample(ucMembers, example);
		
	}

	@Override
	public int checkEmail(UcMembersCheckEmailRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andEmailEqualTo(request.getEmail());
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		return list.size();
	}

	@Override
	public int checkMobilephone(UcMembersCheckeMobileRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andMobilephoneEqualTo(request.getMobilephone());
		List<UcMembers> list  = MapperFactory.getUcMembersMapper().selectByExample(example);
		return list.size();
	}

	

	@Override
	@Transactional
	public UcMembers getSalt(UcMembersLoginRequest request){
		
		String reqUsername = request.getUsername();
		String loginmode = request.getLoginmode();
		//邮箱密码登录
		if(UcMembersLoginModeEnum.EMAILPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setEmail(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);

			return obj;
		}
		//手机动态密码
		else if(UcMembersLoginModeEnum.PHONEDYNPASS_MODE.equals(loginmode)){
			return null;
		}
		//手机密码
		else if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setMobilephone(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);

			return obj;
		}
		//用户名密码
		else if(UcMembersLoginModeEnum.USERPASS_MODE.equals(loginmode)){
			UcMembers record = new UcMembers();
			record.setUsername(reqUsername);
			UcMembers obj = MapperFactory.getUcMembersMapper().selectSalt(record);
			return obj;
		}
		return null;
	
	}

	@Override
	public UcMembers getUcMembersbyUid(Integer uid) {
		
		return MapperFactory.getUcMembersMapper().selectByPrimaryKey(uid);
	}

	@Override
	public String insertMemberPo(UcMembers ucMembers) {
		// TODO Auto-generated method stub
		int insertCount =  MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			Integer newId = MapperFactory.getUcMembersMapper().selectPrimaryKey(ucMembers).getUid();	
			return newId+"";
		}else{
			return "";
		}
	}




}
