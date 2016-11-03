package com.ai.yc.ucenter.service.business.members.impl;


import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.BaseInfo;
import com.ai.opt.base.vo.ResponseHeader;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse;
import com.ai.yc.ucenter.constants.CheckEmailResultCodeConstants;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.EditPassResultCodeConstants;
import com.ai.yc.ucenter.constants.LoginResultCodeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.ai.yc.ucenter.util.BeanValidators;
import com.ai.yc.ucenter.util.LoginValidators;
import com.ai.yc.ucenter.util.PasswordMD5Util;


@Component
@Transactional
public class UcMembersBusinessService implements IUcMembersBusinessService {

	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;

	@Autowired
	protected Validator validator;
	
	@Override
	public UcMembers getUcMembers(UcMembersLoginRequest request){
		UcMembers ucMembers = iUcMembersAtomService.getSalt(request);
		return ucMembers;
	}
	
	
	@Override
	public UcMembersLoginResponse loginMember(UcMembersLoginRequest request) {
		UcMembersLoginResponse response = new UcMembersLoginResponse();
		
		
		try{
			BeanValidators.validateWithException(validator, request);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			ResponseHeader responseHeader=new ResponseHeader(false,LoginResultCodeConstants.ERROR_CODE,list.toString());
			response.setResponseHeader(responseHeader);
			return response;
		}
		
		UcMembers ucMembers = getUcMembers(request);

		if(!LoginValidators.validateEnablestatus(ucMembers)){
			ResponseHeader responseHeader=new ResponseHeader(false,LoginResultCodeConstants.ERROR_CODE,"认证失败,账号未激活");
			response.setResponseHeader(responseHeader);
			return response;
		}
		
		if(StringUtils.isBlank(ucMembers.getSalt())){
			ResponseHeader responseHeader=new ResponseHeader(false,LoginResultCodeConstants.ERROR_CODE,"认证失败");
			response.setResponseHeader(responseHeader);
			return response;
		}

		String passMd5 = PasswordMD5Util.getPassSaltMd5(request.getPassword(),ucMembers.getSalt());

		List<UcMembers> list = iUcMembersAtomService.loginMember(request.getUsername(),passMd5,request.getLoginmode());
		if(list.size()>0){
			ResponseHeader responseHeader=new ResponseHeader(true,LoginResultCodeConstants.SUCCESS_CODE,"认证成功");
			UcMembers ucMembersResponse = list.get(0);
			response.setUid(ucMembersResponse.getUid());
			response.setEmail(ucMembersResponse.getEmail());
			response.setMobilephone(ucMembersResponse.getMobilephone());
//			response.setPassword(ucMembersResponse.getPassword());
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,LoginResultCodeConstants.ERROR_CODE,"认证失败");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	@Override
	public UcMembersLoginResponse insertMember(UcMembersRegisterRequest request) {
		UcMembersRegisterResponse response = new UcMembersRegisterResponse();
		try {
			String result = iUcMembersAtomService.insertMember(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}

	@Override
	public UcMembersGetResponse getMember(UcMembersGetRequest request) {
		UcMembersGetResponse response = new UcMembersGetResponse();
		return iUcMembersAtomService.getMember(request);
	}

	@Override
	public UcMembersResponse updateMobilephone(UcMembersEditMobileRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		int resultCount = iUcMembersAtomService.updateMobilephone(request);
		if(resultCount>0){
			ResponseHeader responseHeader=new ResponseHeader(true,EditMobileResultCodeConstants.SUCCESS_CODE,"认证成功");
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,EditMobileResultCodeConstants.EXISTS_ERROR,"认证失败");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	@Override
	public UcMembersResponse updateEmail(UcMembersEditEmailRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		int resultCount = iUcMembersAtomService.updateEmail(request);
		if(resultCount>0){
			ResponseHeader responseHeader=new ResponseHeader(true,EditMobileResultCodeConstants.SUCCESS_CODE,"认证成功");
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,EditMobileResultCodeConstants.EXISTS_ERROR,"认证失败");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	@Override
	public UcMembersResponse updatePassword(UcMembersEditPassRequest request) {
		UcMembersResponse response = new UcMembersResponse();

		
		
		int resultCount = iUcMembersAtomService.updatePassword(request);
		
		if(resultCount>0){
			ResponseHeader responseHeader=new ResponseHeader(true,EditPassResultCodeConstants.SUCCESS_CODE,"认证成功");
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,EditPassResultCodeConstants.NONERECORD_ERROR,"没有生效记录，修改失败");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	@Override
	public UcMembersResponse ucCheckeEmail(UcMembersCheckEmailRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		int resultCount = iUcMembersAtomService.checkEmail(request);
		if(resultCount>0){
			ResponseHeader responseHeader=new ResponseHeader(true,CheckEmailResultCodeConstants.EXIST_ERROR,"失败，该 Email 已经被注册");
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,CheckEmailResultCodeConstants.SUCCESS_CODE,"成功");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	@Override
	public UcMembersResponse ucCheckeMobilephone(UcMembersCheckeMobileRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		int resultCount = iUcMembersAtomService.checkMobilephone(request);
		if(resultCount>0){
			ResponseHeader responseHeader=new ResponseHeader(true,CheckMobilResultCodeConstants.EXIST_ERROR,"失败，该 Email 已经被注册");
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,CheckMobilResultCodeConstants.SUCCESS_CODE,"成功");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}


	
}
