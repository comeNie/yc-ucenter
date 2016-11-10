package com.ai.yc.ucenter.service.business.members.impl;




import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.EditMobileResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.constants.ResultCodeConstants;
import com.ai.yc.ucenter.dao.mapper.bo.UcMembers;
import com.ai.yc.ucenter.service.atom.members.IUcMembersAtomService;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;
import com.ai.yc.ucenter.util.OperationValidateUtils;
import com.ai.yc.ucenter.util.UCDateUtils;
import com.ai.yc.ucenter.util.UcmembersValidators;



@Component
@Transactional
public class UcMembersOperationBusinessService extends UcMembersBusinessService implements IUcMembersOperationBusinessService {
	@Autowired
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	
	@Autowired
	private IUcMembersAtomService iUcMembersAtomService;
	@Override
	public UcMembersGetOperationcodeResponse saveOperationcode(UcMembersGetOperationcodeRequest request) {
		UcMembersGetOperationcodeResponse response = new UcMembersGetOperationcodeResponse();
		String operationtype = request.getOperationtype();
		//手机激活码、动态密码、邮箱验证码、邮箱激活连接发出后，60秒后可再次获取
		
		
		//校验：Uid只有手机/邮箱验证码和邮箱激活码用到，有值。
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype) || OperationtypeConstants.MOBILE_VALI.equals(operationtype)
				|| OperationtypeConstants.EMAIL_VALI.equals(operationtype)){
			if(!StringUtils.isNotBlank(request.getUid()+"")){

				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "Uid不能为空", null);
				return response;
			}
		}
		String responseUid = "";
		//1、生成手机激活码操作
		if(OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号已被注册", null);
				return response;
			}
			
			//如果没有注册过，需根据手机号注册账号，账号状态为未激活，生成并返回手机激活码		
			else{
				UcMembers mobileUcmebers = new UcMembers();
				mobileUcmebers.setMobilephone(request.getUserinfo());
				mobileUcmebers.setUsername(request.getUserinfo());
				mobileUcmebers.setEnablestatus("0");
				
				mobileUcmebers.setEmailcheck(0); 
				Integer regdate =(int)UCDateUtils.getSystime() ;
				mobileUcmebers.setRegdate(regdate);
				mobileUcmebers.setLastloginip("0");
				
				mobileUcmebers.setLogincount(0);
				mobileUcmebers.setModifydate(0);
			    responseUid = iUcMembersAtomService.insertMemberPo(mobileUcmebers);
		

			
			}
		}//2、手机动态密码
		else if(OperationtypeConstants.DYN_PASS.equals(operationtype)){
			//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
			if(!UcmembersValidators.validateMobilephone(request.getUserinfo())){
				response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.EXIST_ERROR, "该手机号已被注册", null);
				return response;
			}
		}

		String operationcode =  iUcMembersOperationAtomService.saveOperationcode(request);
		if(StringUtils.isNotBlank(operationcode)){
			
			
			Map<Object, Object> responseDate = new HashMap<Object, Object>();
			responseDate.put("uid", responseUid);
			responseDate.put("operationcode",operationcode);
			response = (UcMembersGetOperationcodeResponse) addResponse(response,true,CheckMobilResultCodeConstants.SUCCESS_CODE, "成功", responseDate);
		}
		return response;
	}

	@Override
	@Transactional
	public UcMembersResponse checkActiveMembe(UcMembersActiveRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		String operationtype = request.getOperationtype();
		String operationcode = request.getOperationcode();
		
		List<String > listValidator  = beanValidator(request);
		if(listValidator != null&&!listValidator.isEmpty()){
			response = (UcMembersResponse) addResponse(response,true,ResultCodeConstants.ERROR_CODE, listValidator+"", null);
			return response;
		}
		//判断激活码
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype)
				||OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			if(!OperationValidateUtils.mobileActivAndDyan(request.getUid(), operationcode)){
	
				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期", null);
				return response;
			}
			
			int resultActive =   iUcMembersOperationAtomService.updateActiveMember(request);
			if(resultActive>0){
				
				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, "成功", null);
			}
			
		}//判断验证码
		else{
			if(!OperationValidateUtils.emailVali(request.getUid(), operationcode)){

				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.OVERDUE_ERROR, "验证码过期", null);
				
			}else{
				response = (UcMembersResponse) addResponse(response,true,EditMobileResultCodeConstants.SUCCESS_CODE, "成功", null);
			}
		}
		return response;
	}

}
