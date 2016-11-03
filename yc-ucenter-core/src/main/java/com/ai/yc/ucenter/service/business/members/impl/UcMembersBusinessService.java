package com.ai.yc.ucenter.service.business.members.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.ResponseHeader;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMemEditPassbersResponse;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse;
import com.ai.yc.ucenter.constants.CheckEmailResultCodeConstants;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.Constants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.EditPassResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.constants.RegResultCodeConstants;
import com.ai.yc.ucenter.constants.ResultCodeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.ai.yc.ucenter.util.BeanValidators;
import com.ai.yc.ucenter.util.LoginValidators;
import com.ai.yc.ucenter.util.PasswordMD5Util;
import com.ai.yc.ucenter.util.RegisterValidators;


@Component
@Transactional
public class UcMembersBusinessService implements IUcMembersBusinessService {

	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	
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
			ResponseHeader responseHeader=new ResponseHeader(false,ResultCodeConstants.ERROR_CODE,list.toString());
			response.setResponseHeader(responseHeader);
			return response;
		}
		
		UcMembers ucMembers = getUcMembers(request);

		if(!LoginValidators.validateEnablestatus(ucMembers)){
			ResponseHeader responseHeader=new ResponseHeader(false,ResultCodeConstants.ERROR_CODE,"认证失败,账号未激活");
			response.setResponseHeader(responseHeader);
			return response;
		}
		
		if(StringUtils.isBlank(ucMembers.getSalt())){
			ResponseHeader responseHeader=new ResponseHeader(false,ResultCodeConstants.ERROR_CODE,"认证失败");
			response.setResponseHeader(responseHeader);
			return response;
		}

		String passMd5 = PasswordMD5Util.getPassSaltMd5(request.getPassword(),ucMembers.getSalt());

		List<UcMembers> list = iUcMembersAtomService.loginMember(request.getUsername(),passMd5,request.getLoginmode());
		if(list.size()>0){
			ResponseHeader responseHeader=new ResponseHeader(true,ResultCodeConstants.SUCCESS_CODE,"认证成功");
			UcMembers ucMembersResponse = list.get(0);
			response.setUid(ucMembersResponse.getUid());
			response.setEmail(ucMembersResponse.getEmail());
			response.setMobilephone(ucMembersResponse.getMobilephone());
//			response.setPassword(ucMembersResponse.getPassword());
			response.setResponseHeader(responseHeader);
		}else{
			ResponseHeader responseHeader=new ResponseHeader(false,ResultCodeConstants.ERROR_CODE,"认证失败");
			response.setResponseHeader(responseHeader);
		}
		return response;
	}

	/**
	 * 用户注册
	 */
	@Override
	public UcMembersRegisterResponse insertMember(UcMembersRegisterRequest request) {
		UcMembersRegisterResponse response = new UcMembersRegisterResponse();
		//验证 用户名电话邮箱必输一项
		if(!RegisterValidators.validateUserAccount(request)){
			ResponseHeader responseHeader=new ResponseHeader(false,RegResultCodeConstants.FAIL_CODE,"注册失败，用户名、电话邮箱必输一项");
			response.setResponseHeader(responseHeader);
			return response;
		}
	
		//手机+密码方式手机激活码(operationcode)有值	
		if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(request.getLoginmode()) && StringUtils.isBlank(request.getOperationcode())){
			ResponseHeader responseHeader=new ResponseHeader(false,RegResultCodeConstants.FAIL_CODE,"手机验证码不能为空");
			response.setResponseHeader(responseHeader);
			return response;
		}
		
		
		try{
			BeanValidators.validateWithException(validator, request);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			ResponseHeader responseHeader=new ResponseHeader(false,RegResultCodeConstants.FAIL_CODE,list.toString());
			response.setResponseHeader(responseHeader);
			return response;
		}
	
		
		
		try {
			String resultUid = iUcMembersAtomService.insertMember(request);
			//生成验证码并发送
		
			UcMembersGetOperationcodeRequest getOperaRequest = new UcMembersGetOperationcodeRequest();
			getOperaRequest.setUserinfo(getUserinfoAndOper(request).get("userinfo").toString());
			getOperaRequest.setOperationtype(getUserinfoAndOper(request).get("operationtype").toString());
			String code = iUcMembersOperationAtomService.saveOperationcode(getOperaRequest);
			ResponseHeader responseHeader=new ResponseHeader(false,RegResultCodeConstants.SUCCESS_CODE,"注册成功");
			response.setResponseHeader(responseHeader);
			response.setOperationcode(code);
			response.setUid(resultUid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return response;
	}

	private static Map getUserinfoAndOper(UcMembersRegisterRequest request){
		Map<String,String> map = new HashMap<String,String>();
		String loginway = request.getLoginway();
		if(Constants.LoginWayConstant.EMAIL_PASS.equals(loginway)){
			map.put("userinfo", Constants.UserInfoConstant.EMAIL_INFO);
			map.put("operationtype", OperationtypeConstants.EMAIL_ACTIV);
		}else if(Constants.LoginWayConstant.MOBILE_DYNA.equals(loginway)||Constants.LoginWayConstant.MOBILE_PASS.equals(loginway)){
			map.put("userinfo", Constants.UserInfoConstant.MOBILE_INFO);
			map.put("operationtype", OperationtypeConstants.MOBILE_ACTIV);
		}

		return map;
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

	/**
	 * 修改密码
	 */
	@Override
	public UcMembersResponse updatePassword(UcMembersEditPassRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		//1、校验入参必填
		try{
			BeanValidators.validateWithException(validator, request);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			ResponseHeader responseHeader=new ResponseHeader(false,ResultCodeConstants.ERROR_CODE,list.toString());
			response.setResponseHeader(responseHeader);
			return response;
		}
		//2、验证方式为旧密码
		 String checke_mode = request.getChecke_code();
		 if(("1").equals(checke_mode)){
			 
			 //2.1 验证密码是否输入正确
			 UcMembers ucMembers = iUcMembersAtomService.getUcMembersbyUid(request.getUid());
			
			 String oldpass = request.getChecke_code();
			 String oldpassMD5 = PasswordMD5Util.getPassSaltMd5(oldpass,ucMembers.getSalt());

			 if(!oldpassMD5.equals(ucMembers.getPassword())){
					ResponseHeader responseHeader=new ResponseHeader(false,EditPassResultCodeConstants.OLDPASS_ERROR,"旧密码输入有误，修改失败");
					response.setResponseHeader(responseHeader);
					return response;
			 } //2.2 封装修改密码对象
			 else{
				 String newpass = request.getChecke_code();
				 //生成新salt
				 String salt = PasswordMD5Util.getSalt();
				 String newpassMD5 = PasswordMD5Util.getPassSaltMd5(newpass,salt);
				 ucMembers.setPassword(newpassMD5);
				 ucMembers.setSalt(salt);
				int resultCount = iUcMembersAtomService.updatePassword(ucMembers);
				if(resultCount>0){
					ResponseHeader responseHeader=new ResponseHeader(true,EditPassResultCodeConstants.SUCCESS_CODE,"认证成功");
					response.setResponseHeader(responseHeader);
				}else{
					ResponseHeader responseHeader=new ResponseHeader(false,EditPassResultCodeConstants.NONERECORD_ERROR,"没有生效记录，修改失败");
					response.setResponseHeader(responseHeader);
				}
				return response;
			 }
			 
			
			
		 }
		
		//3、验证方式为验证码

		
		
	
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
