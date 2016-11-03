package com.ai.yc.ucenter.service.atom.members.impl;


import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.ai.opt.sdk.components.sequence.util.SeqUtil;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetModeEnum;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembersCriteria.Criteria;
import com.ai.yc.ucenter.dao.mapper.factory.MapperFactory;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;


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
		UcMembers ucMembers = (UcMembers) request.clone();
		Long newId =SeqUtil.getNewId("SYS$SYSWAITJOBS$ID");

		ucMembers.setUid(newId.intValue());
		
		int insertCount = MapperFactory.getUcMembersMapper().insert(ucMembers);
		if(insertCount>0){
			return newId.toString();
		}else{
			return null;
		}

	}

	@Override
	public UcMembersGetResponse getMember(UcMembersGetRequest request) {
		UcMembersGetResponse response = new UcMembersGetResponse();
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		String reqUsername = request.getUsername();
		//用户id获取
		if(UcMembersGetModeEnum.USERID_ENUM.equals(request.getGetmode())){
			criteria.andUidEqualTo(Integer.parseInt(reqUsername));
		}//邮箱获取
		else if(UcMembersGetModeEnum.EMAIL_ENUM.equals(request.getGetmode())){
			criteria.andEmailEqualTo(reqUsername);
		}//手机获取
		else if(UcMembersGetModeEnum.MOBILE_ENUM.equals(request.getGetmode())){
			criteria.andMobilephoneEqualTo(reqUsername);
		}//用户名获取
		else if(UcMembersGetModeEnum.USERNAME_MODE.equals(request.getGetmode())){
			criteria.andUsernameEqualTo(reqUsername);
		}
		MapperFactory.getUcMembersMapper().selectByExample(example);
	
		return response;
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
	public int updatePassword(UcMembersEditPassRequest request) {
		UcMembersCriteria example = new UcMembersCriteria();
		Criteria criteria = example.createCriteria();
		criteria.andUidEqualTo(request.getUid());
		UcMembers ucMembers = new UcMembers();
		ucMembers.setPassword(request.getNewpw());
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


}
