package com.ai.yc.ucenter.service.business.members.impl;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ai.opt.base.vo.ResponseHeader;
import com.ai.yc.ucenter.api.members.param.UcMembersResponse;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersActiveRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeRequest;
import com.ai.yc.ucenter.api.members.param.opera.UcMembersGetOperationcodeResponse;
import com.ai.yc.ucenter.constants.ActiveOperResultCodeConstants;
import com.ai.yc.ucenter.constants.CheckMobilResultCodeConstants;
import com.ai.yc.ucenter.constants.OperationtypeConstants;
import com.ai.yc.ucenter.service.atom.members.IUcMembersOperationAtomService;
import com.ai.yc.ucenter.service.business.members.IUcMembersOperationBusinessService;



@Component
@Transactional
public class UcMembersOperationBusinessService implements IUcMembersOperationBusinessService {
	
	private IUcMembersOperationAtomService iUcMembersOperationAtomService;
	@Override
	public UcMembersGetOperationcodeResponse saveOperationcode(UcMembersGetOperationcodeRequest request) {
		UcMembersGetOperationcodeResponse response = new UcMembersGetOperationcodeResponse();
		String operationtype = request.getOperationtype();
		
		
		//首先判断手机号是否被注册的合法性，如果注册过直接返回获取失败
		
		//如果没有注册过，需根据手机号注册账号，账号状态为未激活，生成并返回手机激活码。
		
		
		String operationcode =  iUcMembersOperationAtomService.saveOperationcode(request);
		
		return response;
	}

	@Override
	public UcMembersResponse checkActiveMembe(UcMembersActiveRequest request) {
		UcMembersResponse response = new UcMembersResponse();
		String operationtype = request.getOperationtype();
		//判断激活码
		if(OperationtypeConstants.EMAIL_ACTIV.equals(operationtype)
				||OperationtypeConstants.MOBILE_ACTIV.equals(operationtype)){
			int resultCount = iUcMembersOperationAtomService.getActiveMembe(request);
			if(resultCount>0){
				ResponseHeader responseHeader=new ResponseHeader(true,ActiveOperResultCodeConstants.SUCCESS_CODE,"失败，该 Email 已经被注册");
//				response.setResponseHeader(responseHeader);
			}else{
				ResponseHeader responseHeader=new ResponseHeader(false,ActiveOperResultCodeConstants.VAL_ERROR,"成功");
//				response.setResponseHeader(responseHeader);
			}
			
		}//判断验证码
		else{
			
		}
		return response;
	}
	
}
