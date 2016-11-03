package com.ai.yc.ucenter.api.members.impl;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ai.opt.base.exception.BusinessException;
import com.ai.opt.base.exception.SystemException;
import com.ai.yc.ucenter.api.members.interfaces.IUcMembersSV;
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
import com.ai.yc.ucenter.service.business.members.IUcMembersBusinessService;
import com.alibaba.dubbo.config.annotation.Service;

@Service
@Component
public class UcMembersSVImpl implements IUcMembersSV {
	@Autowired
	private IUcMembersBusinessService iUcMembersBusinessService;

	@Override
	public UcMembersLoginResponse ucLoginMember(UcMembersLoginRequest request)
			throws BusinessException, SystemException {

		return iUcMembersBusinessService.loginMember(request);
	}

	@Override
	public UcMembersLoginResponse ucRegisterMember(UcMembersRegisterRequest request)
			throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.insertMember(request);
	}

	@Override
	public UcMembersGetResponse ucGetMember(UcMembersGetRequest request) throws BusinessException, SystemException {
		
		return iUcMembersBusinessService.getMember(request);
	}

	@Override
	public UcMembersResponse ucEditMobilephone(UcMembersEditMobileRequest request)
			throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.updateMobilephone(request);
	}

	@Override
	public UcMembersResponse ucEditEmail(UcMembersEditEmailRequest request) throws BusinessException, SystemException {
	
		return iUcMembersBusinessService.updateEmail(request);
	}

	@Override
	public UcMembersResponse ucEditPassword(UcMembersEditPassRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersBusinessService.updatePassword(request);
	}

	@Override
	public UcMembersResponse ucCheckeEmail(UcMembersCheckEmailRequest request)
			throws BusinessException, SystemException {
		return iUcMembersBusinessService.ucCheckeEmail(request);
	}

	@Override
	public UcMembersResponse ucCheckeMobilephone(UcMembersCheckeMobileRequest request)
			throws BusinessException, SystemException {
		// TODO Auto-generated method stub
		return iUcMembersBusinessService.ucCheckeMobilephone(request);
	}




	

}
