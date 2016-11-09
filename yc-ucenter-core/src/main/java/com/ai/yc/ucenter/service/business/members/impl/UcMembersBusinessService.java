package com.ai.yc.ucenter.service.business.members.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.ResponseHeader;
import com.ai.opt.sdk.util.BeanUtils;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.base.ResponseCode;
import com.ai.yc.ucenter.api.members.param.base.ResponseMessage;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckEmailRequest;
import com.ai.yc.ucenter.api.members.param.checke.UcMembersCheckeMobileRequest;
import com.ai.yc.ucenter.api.members.param.editemail.UcMembersEditEmailRequest;
import com.ai.yc.ucenter.api.members.param.editmobile.UcMembersEditMobileRequest;
import com.ai.yc.ucenter.api.members.param.editpass.UcMembersEditPassRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetRequest;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse;
import com.ai.yc.ucenter.api.members.param.get.UcMembersGetResponse.UcMembersGetDate;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginModeEnum;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginRequest;
import com.ai.yc.ucenter.api.members.param.login.UcMembersLoginResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterRequest;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse;
import com.ai.yc.ucenter.api.members.param.register.UcMembersRegisterResponse.UcMembersRegisterResponseDate;
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
import com.ai.yc.ucenter.service.base.UcBaseService;
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.ai.yc.ucenter.util.BeanValidators;
import com.ai.yc.ucenter.util.LoginValidators;
import com.ai.yc.ucenter.util.OperationValidateUtils;
import com.ai.yc.ucenter.util.PasswordMD5Util;
import com.ai.yc.ucenter.util.UcmembersValidators;


@Component
@Transactional
public class UcMembersBusinessService  extends UcBaseService{

	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	
	
	
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
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersRegisterResponse) addResponse(response,true,RegResultCodeConstants.FAIL_CODE, listValidator.toString(), null);
			return response;
		}
		//验证 用户名电话邮箱必输一项
		if(!UcmembersValidators.validateUserAccount(request)){
			response = (UcMembersRegisterResponse) addResponse(response,true, RegResultCodeConstants.USERNAME_ERROR, "用户名、电话邮箱必输一项", null);
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(RegResultCodeConstants.USERNAME_ERROR, "用户名、电话邮箱必输一项");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			return response;
		}
		//用户名已经存在
		if(StringUtils.isNotBlank(request.getUsername()) && !UcmembersValidators.validateUsername(request.getUsername())){
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(RegResultCodeConstants.USERNAME_EXISTS, "用户名已存在");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true, RegResultCodeConstants.USERNAME_EXISTS, "用户名已存在", null);
			return response;

		}
		//邮箱存在
		if(StringUtils.isNotBlank(request.getEmail()) && !UcmembersValidators.validateEmail(request.getEmail())){
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(RegResultCodeConstants.EMAIL_REGISTERED, "该邮箱已注册");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true, RegResultCodeConstants.EMAIL_REGISTERED, "该邮箱已注册", null);
			return response;
		}
		
		//手机存在
		if(StringUtils.isNotBlank(request.getMobilephone()) && !UcmembersValidators.validateMobilephone(request.getMobilephone())){
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(RegResultCodeConstants.MOBILE_REGISTERED, "该手机号码已注册");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true, RegResultCodeConstants.MOBILE_REGISTERED, "该手机号码已注册", null);
			return response;
		}
		
		//包含不允许注册的词语
		
		//手机+密码方式手机激活码(operationcode)有值	
		if(UcMembersLoginModeEnum.PHONEPASS_MODE.equals(request.getLoginmode()) && StringUtils.isBlank(request.getOperationcode())){
		
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败,手机激活码不能为空");
//
//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true, RegResultCodeConstants.FAIL_CODE, "失败,手机激活码不能为空", null);
			return response;
		}
		

//		try{
//			BeanValidators.validateWithException(validator, request);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.SUCCESS_CODE, "请求成功");
//			responseCode = new ResponseCode(RegResultCodeConstants.FAIL_CODE, "失败,格式校验错误");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
//			
//			return response;
//		}
	
		
		String resultUid ="";
		try {
			resultUid = iUcMembersAtomService.insertMember(request);
			if(StringUtils.isBlank(resultUid)){
//				ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败，没有返回Uid");
//
//				response.setMessage(responseMessage);
				response = (UcMembersRegisterResponse) addResponse(response,true,RegResultCodeConstants.FAIL_CODE, "失败，没有返回Uid", null);
				return response;
			}
		} catch (Exception e) {
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败，保存用户信息失败");
//
//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true,RegResultCodeConstants.FAIL_CODE, "失败，保存用户信息失败", null);
			return response;
		}
			//生成验证码并发送
		try {
			UcMembersGetOperationcodeRequest getOperaRequest = new UcMembersGetOperationcodeRequest();
			getOperaRequest.setUserinfo(getUserinfoAndOper(request).get("userinfo").toString());
			getOperaRequest.setOperationtype(getUserinfoAndOper(request).get("operationtype").toString());
			getOperaRequest.setUid(Integer.valueOf(resultUid));
			String code = iUcMembersOperationAtomService.saveOperationcode(getOperaRequest);
			
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.SUCCESS_CODE, "注册成功");
			UcMembersRegisterResponseDate date = new UcMembersRegisterResponseDate();
//			responseCode = new ResponseCode(RegResultCodeConstants.SUCCESS_CODE, "用户注册成功");	
//			response.setCode(responseCode);
			date.setOperationcode(code);
			date.setUid(resultUid);
//			response.setMessage(responseMessage);
//			response.setDate(date);
			response = (UcMembersRegisterResponse) addResponse(response,true,RegResultCodeConstants.SUCCESS_CODE, "用户注册成功", date);
		} catch (Exception e) {
//			ResponseMessage responseMessage = new ResponseMessage(true, RegResultCodeConstants.FAIL_CODE, "失败,生成验证码失败");

//			response.setMessage(responseMessage);
			response = (UcMembersRegisterResponse) addResponse(response,true,RegResultCodeConstants.FAIL_CODE, "失败,生成验证码失败", null);
			return response;
		}
		
		
		return response;
	}

	private static Map<String,String> getUserinfoAndOper(UcMembersRegisterRequest request){
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
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersGetResponse) addResponse(response,true,Constants.GetUcMembersResultConstants.NOT_EMPTY, listValidator.toString(), null);
			return response;
		}
		
	/*	try{
			BeanValidators.validateWithException(validator, request);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
		
//			ResponseMessage responseMessage = new ResponseMessage(true, Constants.GetUcMembersResultConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(Constants.GetUcMembersResultConstants.NOT_EMPTY, list.toString());	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			
			response = (UcMembersGetResponse) addResponse(Constants.GetUcMembersResultConstants.NOT_EMPTY, list.toString(), null);
			return response;
		}*/
		
		List<UcMembers> list = iUcMembersAtomService.getMember(request);
		
		if(list.size()>0){
			UcMembers ucMembers = list.get(0);
			//判断账号是否未激活
			if(("0").equals(ucMembers.getEnablestatus())){
			
//				ResponseMessage responseMessage = new ResponseMessage(true, Constants.GetUcMembersResultConstants.SUCCESS_CODE, "成功，账户未激活");
//				ResponseCode responseCode = new ResponseCode(Constants.GetUcMembersResultConstants.NO_ACTIV, "账户未激活");	
				UcMembersGetDate ucMembersGetDate = new UcMembersGetDate();
				
				BeanUtils.copyProperties(ucMembersGetDate, ucMembers);
				//response.setDate(ucMembersGetDate);
//				response.setCode(responseCode);
//				response.setMessage(responseMessage);
				
				response = (UcMembersGetResponse) addResponse(response,true,Constants.GetUcMembersResultConstants.NO_ACTIV, "账户未激活", ucMembersGetDate);
				return response;
			}
		//	ResponseMessage responseMessage = new ResponseMessage(true, Constants.GetUcMembersResultConstants.SUCCESS_CODE, "成功");
		//	ResponseCode responseCode = new ResponseCode(Constants.GetUcMembersResultConstants.SUCCESS_CODE, "成功");	
			UcMembersGetDate ucMembersGetDate = new UcMembersGetDate();
			BeanUtils.copyProperties(ucMembersGetDate, ucMembers);
//			response.setDate(ucMembersGetDate);
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersGetResponse) addResponse(response,true,Constants.GetUcMembersResultConstants.SUCCESS_CODE, "成功", ucMembersGetDate);
			return response;
		}else{
//			ResponseMessage responseMessage = new ResponseMessage(true,Constants.GetUcMembersResultConstants.FAIL_CODE, "失败，为找到该用户信息");
//	
//			response.setMessage(responseMessage);
			response = (UcMembersGetResponse) addResponse(response,true,Constants.GetUcMembersResultConstants.FAIL_CODE, "失败，未找到该用户信息", null);
			return response;
		}

	}

	@Override
	public UcMembersResponse updateMobilephone(UcMembersEditMobileRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.FAIL_CODE, listValidator.toString(), null);
			return response;
		}
//		try{
//			BeanValidators.validateWithException(validator, request);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//		
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
//		
//			response.setMessage(responseMessage);
//			return response;
//		}
		//验证码是否一致,验证码有效期
		if(!OperationValidateUtils.mobileActivAndDyan(request.getUid(), request.getOperationcode())){
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期，修改/绑定失败");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期，修改/绑定失败", null);
			return response;
		}		
		//该电话已被注册
		if(!UcmembersValidators.validateMobilephone(request.getMobilephone())){
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.EXISTS_ERROR, "该电话已经被注册，修改/绑定失败");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.EXISTS_ERROR, "该电话已经被注册，修改/绑定失败", null);
			return response;
		}
		
		int resultCount = iUcMembersAtomService.updateMobilephone(request);
		if(resultCount>0){
			
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.SUCCESS_CODE, "成功");
//			ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.SUCCESS_CODE, "更新成功");	
//			response.setCode(responseCode);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, 
					"更新成功", null);
//			response.setMessage(responseMessage);

		}else{
//			ResponseMessage responseMessage = new ResponseMessage(true, Constants.GetUcMembersResultConstants.FAIL_CODE, "失败");
//
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.FAIL_CODE, 
					"失败", null);
		}
		return response;
	}

	@Override
	public UcMembersResponse updateEmail(UcMembersEditEmailRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		//验证码过期，修改/绑定失败
		if(!OperationValidateUtils.emailVali(request.getUid(), request.getOperationcode())){
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期，修改/绑定失败");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, 
					"验证码过期，修改/绑定失败", null);
			return response;
		}
		
		int resultCount = iUcMembersAtomService.updateEmail(request);
		if(resultCount>0){
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.SUCCESS_CODE, "成功");
//			ResponseCode responseCode = new ResponseCode(EditMobileResultCodeConstants.SUCCESS_CODE, "成功");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, 
					"成功", null);
			
		}else{
//			ResponseMessage responseMessage = new ResponseMessage(true, EditMobileResultCodeConstants.FAIL_CODE, "失败");
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.FAIL_CODE, 
					"失败", null);
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
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.FAIL_CODE, listValidator.toString(), null);
			return response;
		}
		
		
//		try{
//			BeanValidators.validateWithException(validator, request);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//			ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.FAIL_CODE, "失败");
//			response.setMessage(responseMessage);
//			return response;
//		}
		//2、验证方式为旧密码
		 String checke_mode = request.getChecke_code();
		 if(("1").equals(checke_mode)){
			 
			 //2.1 验证密码是否输入正确
			 UcMembers ucMembers = iUcMembersAtomService.getUcMembersbyUid(request.getUid());
			
			 String oldpass = request.getChecke_code();
			 String oldpassMD5 = PasswordMD5Util.getPassSaltMd5(oldpass,ucMembers.getSalt());

			 if(!oldpassMD5.equals(ucMembers.getPassword())){

//					ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.FAIL_CODE, "失败");
//					ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.OLDPASS_ERROR, "旧密码输入有误，修改失败");	
//					response.setMessage(responseMessage);
//					response.setCode(responseCode);
					response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.OLDPASS_ERROR, "旧密码输入有误，修改失败", null);
					return response;
			 } //2.2 封装修改密码对象
			 else{
				 String newpass = request.getChecke_code();
				 //生成新salt
				 String salt = PasswordMD5Util.creatSalt();
				 String newpassMD5 = PasswordMD5Util.getPassSaltMd5(newpass,salt);
				 ucMembers.setPassword(newpassMD5);
				 ucMembers.setSalt(salt);
				int resultCount = iUcMembersAtomService.updatePassword(ucMembers);
				if(resultCount>0){

//					ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.SUCCESS_CODE, "成功");
//					ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.SUCCESS_CODE, "修改成功");	
//					response.setMessage(responseMessage);
//					response.setCode(responseCode);
					response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.SUCCESS_CODE, "修改成功", null);
					
				}else{

//					ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.FAIL_CODE, "失败");
//					ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.NONERECORD_ERROR, "没有生效记录，修改失败");	
//					response.setMessage(responseMessage);
//					response.setCode(responseCode);
					response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.NONERECORD_ERROR, "没有生效记录，修改失败", null);
				}
				return response;
			 }
			 
			
			
		 }
		
		//3、验证方式为验证码 此处验证码都是操作码生
		 else if(("2").equals(checke_mode)){
			 
			 if(!OperationValidateUtils.mobileActivAndDyan(request.getUid(), request.getChecke_code())){
//					ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.FAIL_CODE, "失败");
//					ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.OVERDUE_ERROR, "验证码过期，修改密码失败");	
//					response.setCode(responseCode);
//					response.setMessage(responseMessage);
					response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.OVERDUE_ERROR, "验证码过期，修改密码失败", null);
					return response;
			 }
			 
			 UcMembers ucMembers = new UcMembers();
			 ucMembers.setUid(request.getUid());
			 String salt = PasswordMD5Util.creatSalt();
			 ucMembers.setSalt(salt);
			 ucMembers.setPassword(PasswordMD5Util.getPassSaltMd5(request.getNewpw(), salt));
			int resultCountmobile =  iUcMembersAtomService.updatePassword(ucMembers);
			if(resultCountmobile>0){

//				ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.SUCCESS_CODE, "成功");
//				ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.SUCCESS_CODE, "修改成功");	
//				response.setMessage(responseMessage);
//				response.setCode(responseCode);
				response = (UcMembersResponse) addResponse(response,true,EditPassResultCodeConstants.SUCCESS_CODE, "修改成功", null);
				
			}else{

				ResponseMessage responseMessage = new ResponseMessage(true, EditPassResultCodeConstants.FAIL_CODE, "失败");
				ResponseCode responseCode = new ResponseCode(EditPassResultCodeConstants.NONERECORD_ERROR, "没有生效记录，修改失败");	
				response.setMessage(responseMessage);
				response.setCode(responseCode);
				
			}
		 }
		 return response;

	}

	
	@Override
	public UcMembersResponse ucCheckeEmail(UcMembersCheckEmailRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,CheckEmailResultCodeConstants.FORMAT_ERROR, listValidator.toString(), null);
			return response;
		}
//		try{
//			BeanValidators.validateWithException(validator, request);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//		
////			ResponseMessage responseMessage = new ResponseMessage(true, CheckEmailResultCodeConstants.FAIL_CODE, "失败");
////			ResponseCode responseCode = new ResponseCode(CheckEmailResultCodeConstants.FORMAT_ERROR, "邮箱格式有误");	
////			response.setCode(responseCode);
////			response.setMessage(responseMessage);
//			response = (UcMembersResponse) addResponse(response,true,CheckEmailResultCodeConstants.FORMAT_ERROR, "邮箱格式有误", null);
//			return response;
//		}
		int resultCount = iUcMembersAtomService.checkEmail(request);
		if(resultCount>0){
//			ResponseMessage responseMessage = new ResponseMessage(true, CheckEmailResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(CheckEmailResultCodeConstants.EXIST_ERROR, "该邮箱已被注册");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,CheckEmailResultCodeConstants.EXIST_ERROR, "该邮箱已被注册", null);
		}else{
//			ResponseMessage responseMessage = new ResponseMessage(true, CheckEmailResultCodeConstants.SUCCESS_CODE, "成功");
//			ResponseCode responseCode = new ResponseCode(CheckEmailResultCodeConstants.SUCCESS_CODE, "成功");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,CheckEmailResultCodeConstants.SUCCESS_CODE, "成功", null);
		}
		return response;
	}

	@Override
	public UcMembersResponse ucCheckeMobilephone(UcMembersCheckeMobileRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		List<String > listValidator  = beanValidator(request);
		if(!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,CheckMobilResultCodeConstants.FORMAT_ERROR, listValidator.toString(), null);
			return response;
		}
//		try{
//			BeanValidators.validateWithException(validator, request);
//		}catch(ConstraintViolationException ex){
//			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
//			list.add(0, "数据验证失败：");
//		
//			ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.FORMAT_ERROR, "手机格式有误");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
//			return response;
//		}
		int resultCount = iUcMembersAtomService.checkMobilephone(request);
		if(resultCount>0){
			response = (UcMembersResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号码已被注册", null);
//			ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.FAIL_CODE, "失败");
//			ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号码已被注册");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
		}else{
//			ResponseMessage responseMessage = new ResponseMessage(true, CheckMobilResultCodeConstants.SUCCESS_CODE, "成功");
//			ResponseCode responseCode = new ResponseCode(CheckMobilResultCodeConstants.SUCCESS_CODE, "成功");	
//			response.setCode(responseCode);
//			response.setMessage(responseMessage);
			response = (UcMembersResponse) addResponse(response,true,CheckMobilResultCodeConstants.SUCCESS_CODE, "成功", null);
		}
		return response;
	}


	
}
